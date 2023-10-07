package STE.service;

import STE.infrastructure.database.dao.CompetitorDao;
import STE.infrastructure.database.dao.TournamentDao;
import STE.infrastructure.database.entity.CompetitorEntity;
import STE.infrastructure.database.entity.PersonEntity;
import STE.infrastructure.database.entity.TournamentEntity;
import STE.infrastructure.database.repository.CompetitorRepository;
import STE.infrastructure.database.repository.TournamentRepository;
import STE.service.managment.Categories;
import STE.service.managment.Suppliers;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class CompetitorService {

    private final CompetitorDao competitorDao;
    private final TournamentDao tournamentDao;
    private final PersonService personService;
    private final Suppliers suppliers;

    @Transactional
    public CompetitorEntity createNewCompetitor(PersonEntity person, Integer tournamentId) {
        TournamentEntity tournamentById = tournamentDao.findTournamentById(tournamentId);
        return CompetitorEntity.builder()
                .ageCategories(createAgeCategorie(person))
                .startNumber(createStartNumber(tournamentId))
                .tournamentEntity(tournamentById)
                .build().withPerson(person);
    }

    @Transactional
    public Integer createStartNumber(Integer tournamentId) {
        List<CompetitorEntity> competitorEntities = competitorDao.findAllCompetitorsWithTournamentId(tournamentId);
        int size = competitorEntities.size();
        return size + 1;
    }


    @Transactional
    public void addExistingCompetitorToTournament(String email, int id) {
        PersonEntity personByEmail = personService.findPersonByEmail(email);
        List<CompetitorEntity> allCompetitorsWithTournamentId = findAllCompetitorsWithTournamentId(id);
        List<String> emailList = allCompetitorsWithTournamentId.stream()
                .map(competitor -> competitor.getPerson().getEmail())
                .filter(a -> a.equals(email))
                .toList();

        if(emailList.isEmpty()){
            CompetitorEntity newCompetitor = createNewCompetitor(personByEmail, id);
            competitorDao.saveCompetitor(newCompetitor);
        }else throw new RuntimeException("Competitor already exist on this start list");

    }

    public String createAgeCategorie(PersonEntity person) {

        String dateOfBirth = person.getDateOfBirth();
        DateTimeFormatter df = new DateTimeFormatterBuilder()
                .parseCaseInsensitive()
                .appendPattern("dd-MM-yyyy")
                .toFormatter(Locale.ENGLISH);


        LocalDate dateNow = LocalDate.now();
        String format = dateNow.format(df);
        LocalDate dateNowEur = LocalDate.parse(format, df);
        LocalDate parseDateOfBirth = LocalDate.parse(dateOfBirth, df);
        int ageOfPlayer = dateNow.getYear() - parseDateOfBirth.getYear();

        String ageCategory = null;

        if (ageOfPlayer < 10) {
            ageCategory = Categories.AgeCategories.TO_TEN_YEARS_OLD.toString();
        }
        if (ageOfPlayer >= 10 && ageOfPlayer < 15) {
            ageCategory = Categories.AgeCategories.TEN_TO_FIFTEEN.toString();
        }

        if (ageOfPlayer >= 15 && ageOfPlayer < 20) {
            ageCategory = Categories.AgeCategories.FIFTEEN_TO_TWENTY.toString();
        }


        if (ageOfPlayer >= 20 && ageOfPlayer < 30) {
            ageCategory = Categories.AgeCategories.TWENTY_TO_THIRTY.toString();
        }

        if (ageOfPlayer >= 30 && ageOfPlayer < 40) {
            ageCategory = Categories.AgeCategories.THIRTY_TO_FORTY.toString();
        }

        if (ageOfPlayer >= 40 && ageOfPlayer < 50) {
            ageCategory = Categories.AgeCategories.FORTY_TO_FIFTY.toString();
        }

        if (ageOfPlayer >= 50 && ageOfPlayer < 60) {
            ageCategory = Categories.AgeCategories.MORE_OF_FIFTY.toString();
        }
        return ageCategory;
    }


    public List<CompetitorEntity> findAllCompetitorsWithTournamentId(Integer tournamentId) {
        return competitorDao.findAllCompetitorsWithTournamentId(tournamentId);

    }

    public CompetitorEntity findCompetitorByStartNumber(String startNumber, String tournamentId) {
        return competitorDao.findCompetitorByStartNumberAndTournamentId(startNumber, tournamentId);
    }

    public String createResult(CompetitorEntity competitorEntity) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        String timeStartOfCompetitor = competitorEntity.getTournamentEntity().getStartOfCompetitors();
        LocalDateTime parseTimeStartOfCompetitor = LocalDateTime.parse(timeStartOfCompetitor, formatter);
        LocalDateTime timeNow = suppliers.localDateTimeSupplier();

        long between = ChronoUnit.MILLIS.between(parseTimeStartOfCompetitor, timeNow);

        long hour = (between / (60 * 60 * 1000)) % 24;
        long minutes = (between / (60 * 1000)) % 60;
        long second = (between / 1000) % 60;

        return hour + ":" + minutes + ":" + second;
    }

    public String createAverageSpeed(CompetitorEntity competitorEntity) {
        Integer distanceOfTournament = competitorEntity.getTournamentEntity().getDistance();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        String timeStartOfCompetitor = competitorEntity.getTournamentEntity().getStartOfCompetitors();
        LocalDateTime parseTimeStartOfCompetitor = LocalDateTime.parse(timeStartOfCompetitor, formatter);
        LocalDateTime timeNow = suppliers.localDateTimeSupplier();
        long between = ChronoUnit.SECONDS.between(parseTimeStartOfCompetitor, timeNow);
        double averageSpeed = (double) distanceOfTournament / ((double) between / 3600);
        return String.valueOf(averageSpeed).substring(0, 5);

    }


    public void update(CompetitorEntity competitorByStartNumber) {
        competitorDao.update(competitorByStartNumber);

    }


    public String createPlace(String id, CompetitorEntity competitor) {
        List<CompetitorEntity> allCompetitorsWithTournamentId =
                competitorDao.findAllCompetitorsWithTournamentId(Integer.parseInt(id));

        List<String> listResult = allCompetitorsWithTournamentId.stream()
                .map(CompetitorEntity::getResult)
                .filter(Objects::nonNull).toList();

        return String.valueOf(listResult.size());

    }


    public List<CompetitorEntity> findAllCompetitorsWithPersonId(Integer personId) {
        return competitorDao.findAllCompetitorsWithPersonId(personId);
    }

    public CompetitorEntity findCompetitorById(Integer id) {
        return competitorDao.findCompetitorById(id);
    }

    public void deleteCopmpetitor(CompetitorEntity competitorEntity) {
        competitorDao.deleteCopmpetitor(competitorEntity);
    }
}



