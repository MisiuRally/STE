package STE.service;

import STE.domain.Organizer;
import STE.infrastructure.database.dao.TournamentDao;
import STE.infrastructure.database.entity.OrganizerEntity;
import STE.infrastructure.database.entity.TournamentEntity;
import STE.infrastructure.database.repository.mapper.OrganizerEntityMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Locale;

@Slf4j
@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class TournamentService {


    private final TournamentDao tournamentDao;
    private final OrganizerService organizerService;
    private final OrganizerEntityMapper organizerEntityMapper;



    public List<TournamentEntity> findAllTournaments() {

        return tournamentDao.findAllTournament();
    }

    public TournamentEntity findTournamentById(Integer tournamentId) {

        return tournamentDao.findTournamentById(tournamentId);
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
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime timeNow = LocalDateTime.now();
        String format = formatter.format(timeNow);
        tournamentById.setStartOfCompetitors(format);
        tournamentDao.update(tournamentById);

    }


}

