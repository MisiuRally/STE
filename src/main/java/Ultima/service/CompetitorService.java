package Ultima.service;

import Ultima.API.DTO.CompetitorDTO;
import Ultima.API.DTO.PersonDTO;
import Ultima.API.DTO.TournamentDTO;
import Ultima.API.DTO.mapper.CompetitorMapperDTO;
import Ultima.API.DTO.mapper.PersonMapperDTO;
import Ultima.API.DTO.mapper.TournamentMapperDTO;
import Ultima.domain.Competitor;
import Ultima.domain.Person;
import Ultima.domain.Tournament;
import Ultima.infrastructure.database.dao.CompetitorDao;
import Ultima.infrastructure.database.dao.PersonDao;
import Ultima.infrastructure.database.dao.TournamentDao;
import Ultima.infrastructure.database.entity.CompetitorEntity;
import Ultima.infrastructure.database.entity.PersonEntity;
import Ultima.infrastructure.database.entity.TournamentEntity;
import Ultima.infrastructure.database.repository.jpa.CompetitorJpaRepository;
import Ultima.infrastructure.database.repository.mapper.CompetitorEntityMapper;
import Ultima.infrastructure.database.repository.mapper.PersonEntityMapper;
import Ultima.infrastructure.database.repository.mapper.TournamentEntityMapper;
import Ultima.service.managment.Categories;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class CompetitorService {

    private final CompetitorDao competitorDao;
    private final TournamentDao tournamentDao;
    private final PersonService personService;


    public CompetitorEntity createNewCompetitor(String email, Integer tournamentId) {
        PersonEntity personEntity = personService.findPersonByEmail(email);
        TournamentEntity tournamentById = tournamentDao.findTournamentById(tournamentId);
        return CompetitorEntity.builder()
                .ageCategories(createAgeCategorie(personEntity))
                .startNumber(createStartNumber(tournamentId))
                .person(personEntity)
                .tournamentEntity(tournamentById)
                .build();
    }

    public CompetitorEntity createNewCompetitor(PersonEntity person, Integer tournamentId) {
        TournamentEntity tournamentById = tournamentDao.findTournamentById(tournamentId);
        return CompetitorEntity.builder()
                .ageCategories(createAgeCategorie(person))
                .startNumber(createStartNumber(tournamentId))
                .tournamentEntity(tournamentById)
                .build().withPerson(person);
    }

    private Integer createStartNumber(Integer tournamentId) {
        List<CompetitorEntity> competitorEntities = competitorDao.findAllCompetitorsWithTournamentId(tournamentId);
        Integer size = competitorEntities.size();
        return size + 1;
    }


    @Transactional
    public void saveNewCompetitor(CompetitorEntity competitorEntity) {

        competitorDao.saveCompetitor(competitorEntity);

    }

    public void addExistingCompetitorToTournament(String email, int id) {
        PersonEntity personByEmail = personService.findPersonByEmail(email);
        CompetitorEntity newCompetitor = createNewCompetitor(personByEmail, id);
        competitorDao.saveCompetitor(newCompetitor);


    }

    public CompetitorEntity findCompetitorByEmail(String email) {
        return competitorDao.findCompetitorByEmail(email);


    }


    private String createAgeCategorie(PersonEntity person) {

        String dateOfBirth = person.getDateOfBirth();


        DateTimeFormatter df = new DateTimeFormatterBuilder()
                .parseCaseInsensitive()
                .appendPattern("dd-MM-yyyy")
                .toFormatter(Locale.ENGLISH);


        LocalDate dateNow = LocalDate.now();
        String format = dateNow.format(df);
        LocalDate dateNowEur = LocalDate.parse(format, df);
        LocalDate parseDateOfBirth = LocalDate.parse(dateOfBirth, df);
        Integer ageOfPlayer = dateNow.getYear() - parseDateOfBirth.getYear();

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

    public String createResult(CompetitorEntity competitorEntity) throws ParseException {
        SimpleDateFormat sdf1
                = new SimpleDateFormat(
                "HH:mm:ss");
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");
        String timeNow = LocalDateTime.now().format(dtf).toString();
        String timeStart = competitorEntity.getTournamentEntity().getStartOfCompetitors();

        Date time1 = sdf1.parse(timeNow);
        Date time2 = sdf1.parse(timeStart);

        long difference = time1.getTime()-time2.getTime();

        long hour = (difference / (60 * 60 * 1000)) % 24;
        long minutes = (difference / (60 * 1000)) % 60;
        long second = (difference / 1000) % 60;

        return hour + ":" + minutes + ":" + second;


    }

    public void update(CompetitorEntity competitorByStartNumber) {
        competitorDao.update(competitorByStartNumber);

    }

    public String createAverageSpeed(CompetitorEntity competitorEntity) throws ParseException {
        Integer distanceOfTournament = competitorEntity.getTournamentEntity().getDistance();
        SimpleDateFormat sdf1
                = new SimpleDateFormat(
                "HH:mm:ss");
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");
        String timeNow = LocalDateTime.now().format(dtf).toString();
        String timeStart = competitorEntity.getTournamentEntity().getStartOfCompetitors();

        Date time1 = sdf1.parse(timeNow);
        Date time2 = sdf1.parse(timeStart);

        long difference = time1.getTime()-time2.getTime();
        long second = (difference / 1000);


        double averageSpeed = (double) distanceOfTournament /((double) second /3600);

        return String.valueOf(averageSpeed).substring(0,5);

    }
}
