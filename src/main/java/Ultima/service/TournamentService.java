package Ultima.service;

import Ultima.API.DTO.CompetitorDTO;
import Ultima.API.DTO.OrganizerDTO;
import Ultima.API.DTO.TournamentDTO;
import Ultima.API.DTO.mapper.CompetitorMapperDTO;
import Ultima.API.DTO.mapper.OrganizerMapperDTO;
import Ultima.API.DTO.mapper.TournamentMapperDTO;
import Ultima.domain.Competitor;
import Ultima.domain.Organizer;
import Ultima.domain.Tournament;
import Ultima.domain.TournamentCompetitor;
import Ultima.infrastructure.database.dao.CompetitorDao;
import Ultima.infrastructure.database.dao.TournamentCompetitorDao;
import Ultima.infrastructure.database.dao.TournamentDao;
import Ultima.infrastructure.database.entity.CompetitorEntity;
import Ultima.infrastructure.database.entity.OrganizerEntity;
import Ultima.infrastructure.database.entity.TournamentCompetitorEntity;
import Ultima.infrastructure.database.entity.TournamentEntity;
import Ultima.infrastructure.database.repository.mapper.CompetitorEntityMapper;
import Ultima.infrastructure.database.repository.mapper.OrganizerEntityMapper;
import Ultima.infrastructure.database.repository.mapper.TournamentEntityMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoUnit;
import java.util.Collection;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class TournamentService {


    private final TournamentDao tournamentDao;
    private final OrganizerService organizerService;
    private final OrganizerEntityMapper organizerEntityMapper;


    public void joinCompetitorWithTournament(CompetitorEntity competitorEntity, int id) {

        TournamentEntity tournamentById = tournamentDao.findTournamentById(id);

        tournamentById.setCompetitor(List.of(competitorEntity));

        log.info("#####################################################");
        log.info("Competitor" + tournamentById.getCompetitor().toString());
        log.info("Tournamentid" + tournamentById.getTournamentId().toString());
        log.info("Competitor email" + tournamentById.getCompetitor().stream().map(a -> a.getPerson().getEmail()).toString());
        log.info("#####################################################");
//        tournamentDao.save(tournamentById);
        TournamentEntity tournamentByAfterSave = tournamentDao.findTournamentById(id);
        log.info("#####################################################");
        log.info(LocalDateTime.now().toString());
        log.info("Competitor" + tournamentByAfterSave.getCompetitor().toString());
        log.info(tournamentByAfterSave.getCompetitor().stream().map(CompetitorEntity::getCompetitorId).toString());
        log.info("#####################################################");
        System.out.println(tournamentByAfterSave.getCompetitor());

    }


    public List<TournamentEntity> findAllTournaments() {

        return tournamentDao.findAllTournament();
    }

    public TournamentEntity findTournamentById(Integer tournamentId) {
        TournamentEntity tournamentById = tournamentDao.findTournamentById(tournamentId);

        return tournamentById;
    }

    public TournamentEntity createNewTournament(String nameOfTournament, Integer numberOfStartPlates,
                                                String sportCategory, String startOfTournament,
                                                String endOfTournament, Integer distance, BigDecimal buyIn, String email) {
        Organizer organizer = organizerService.findOrganizerByEmail(email);
        OrganizerEntity organizerEntity = organizerEntityMapper.mapperToEntity(organizer);


        TournamentEntity tournamentEntity = TournamentEntity.builder()
                .nameOfTournament(nameOfTournament)
                .numberOfStartPlates(numberOfStartPlates)
                .sportCategory(sportCategory)
                .startOfTournament(startOfTournament)
                .endOfTournament(endOfTournament)
                .distance(distance)
                .buyIn(buyIn)
                .build();
        tournamentEntity.setOrganizer(organizerEntity);
        return tournamentEntity;

    }

    public void saveNewTournament(TournamentEntity tournamentEntity) {
        tournamentDao.save(tournamentEntity);
    }

    public List<TournamentEntity> findAllTournamentsByOrganizerEmail(String email) {
        return tournamentDao.findAllTournamentsByOrganizerEmail(email);


    }

    public void updateStartNumbers(Integer tournamentId, Integer numberOfStartPlates) {
        TournamentEntity tournamentById = tournamentDao.findTournamentById(tournamentId);
        DateTimeFormatter dateTimeFormatter = new DateTimeFormatterBuilder()
                .parseCaseInsensitive()
                .appendPattern("dd-MM-yyyy")
                .toFormatter(Locale.ENGLISH);
        LocalDate now = LocalDate.now();


        LocalDate startOfTournament = LocalDate.parse(tournamentById.getStartOfTournament(), dateTimeFormatter);
        long between = ChronoUnit.MONTHS.between(now, startOfTournament);

        if (between >= 2) {
            tournamentById.setNumberOfStartPlates(numberOfStartPlates);
            tournamentDao.update(tournamentById);
        } else
            throw new RuntimeException("You can not change start numbers. Too short time to start of tournament.");
    }

    public void updateDateOfTournament(Integer tournamentId, String newStartDate, String newEndDate) {
        TournamentEntity tournamentById = tournamentDao.findTournamentById(tournamentId);
        DateTimeFormatter dateTimeFormatter = new DateTimeFormatterBuilder()
                .parseCaseInsensitive()
                .appendPattern("dd-MM-yyyy")
                .toFormatter(Locale.ENGLISH);
        LocalDate now = LocalDate.now();
        LocalDate startOfTournament = LocalDate.parse(tournamentById.getStartOfTournament(), dateTimeFormatter);
        long between = ChronoUnit.MONTHS.between(now, startOfTournament);
        if (between >= 2) {
            tournamentById.setStartOfTournament(newStartDate);
            tournamentById.setEndOfTournament(newEndDate);
            tournamentDao.update(tournamentById);
        } else
            throw new RuntimeException("You can not change date of tournament. Too short time to start of tournament.");
    }


    public void setTimeOfStart(TournamentEntity tournamentById) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");
        LocalDateTime timeNow = LocalDateTime.now();
        String format = dtf.format(timeNow);
        tournamentById.setStartOfCompetitors(format);
        tournamentDao.update(tournamentById);

    }
}

