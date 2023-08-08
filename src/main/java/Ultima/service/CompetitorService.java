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

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.Locale;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class CompetitorService {

    private final CompetitorEntityMapper competitorEntityMapper;
    private final CompetitorDao competitorDao;
    private final TournamentDao tournamentDao;

    private final PersonEntityMapper personEntityMapper;
    private final TournamentService tournamentService;
    private final PersonService personService;

    private final TournamentEntityMapper tournamentEntityMapper;



    public void saveNewCompetitor(Competitor competitor) {
        CompetitorEntity competitorEntity = competitorEntityMapper.mapperToEntity(competitor);

        competitorDao.saveCompetitor(competitorEntity);

    }

    private Set<CompetitorDTO> setCompetitorToTournament(CompetitorDTO competitorDTO) {
        return Set.of(competitorDTO);
    }

    public Competitor createNewCompetitor(String email, Integer tournamentId) {
        PersonEntity personEntity= personService.findPersonByEmail(email);
        Person person = personEntityMapper.mapperFromEntity(personEntity);
        return Competitor.builder()
                .person(person)
                .startNumber(tournamentService.createStartNumber(tournamentId))
                .ageCategories(createAgeCategorie(person))
                .tournament(addTournamentById(tournamentId))
                .build();

    }

    private Tournament addTournamentById(Integer tournamentId) {

        TournamentEntity tournamentById = tournamentDao.findTournamentById(tournamentId);
       return tournamentEntityMapper.mapperFromEntity(tournamentById);
    }

    private String createAgeCategorie(Person person) {

        String dateOfBirth = person.getDateOfBirth();


        DateTimeFormatter df = new DateTimeFormatterBuilder()
                .parseCaseInsensitive()
                .appendPattern("dd-MM-yyyy")
                .toFormatter(Locale.ENGLISH);


        LocalDate dateNow = LocalDate.now();
        String format = dateNow.format(df);
        LocalDate dateNowEur= LocalDate.parse(format,df);
        LocalDate parseDateOfBirth = LocalDate.parse(dateOfBirth,df);
        Integer ageOfPlayer = dateNow.getYear() - parseDateOfBirth.getYear();

        String ageCategory = null;

        if (ageOfPlayer < 10) {
            ageCategory = Categories.AgeCategories.TO_TEN_YEARS_OLD.toString();
        }
        if (ageOfPlayer > 10 && ageOfPlayer < 15) {
            ageCategory = Categories.AgeCategories.TEN_TO_FIFTEEN.toString();
        }

        if (ageOfPlayer > 15 && ageOfPlayer < 20) {
            ageCategory = Categories.AgeCategories.FIFTEEN_TO_TWENTY.toString();
        }


        if (ageOfPlayer > 20 && ageOfPlayer < 30) {
            ageCategory = Categories.AgeCategories.TWENTY_TO_THIRTY.toString();
        }

        if (ageOfPlayer > 30 && ageOfPlayer < 40) {
            ageCategory = Categories.AgeCategories.THIRTY_TO_FORTY.toString();
        }

        if (ageOfPlayer > 40 && ageOfPlayer < 50) {
            ageCategory = Categories.AgeCategories.FORTY_TO_FIFTY.toString();
        }

        if (ageOfPlayer > 50 && ageOfPlayer < 60) {
            ageCategory = Categories.AgeCategories.MORE_OF_FIFTY.toString();
        }
        return ageCategory;
    }

    public CompetitorEntity findCompetitorByEmail(String email) {
       return competitorDao.findCompetitorByEmail(email);


    }


//    public Competitor findCompetitorByEmail(String email) {
//        Optional<CompetitorEntity> competitorEntity = competitorJpaRepository.findCompetitorByEmail(email);
//
//        if (competitorEntity.isPresent()) {
//            return entityMapper.mapperFromEntity(competitorEntity.get());
//        } else throw new RuntimeException("Competitor with email: [%s] does not exist!".formatted(email));
//
//    }

}
