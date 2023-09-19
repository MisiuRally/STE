package STE.service;

import STE.infrastructure.database.entity.TournamentEntity;
import STE.service.managment.ObjectsCreators;
import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@AllArgsConstructor(onConstructor = @__(@Autowired))
@TestPropertySource(properties = "app.scheduling.enable=false")
class TournamentServiceTest {

    private final TournamentService tournamentService;


    @Test
    void findAllTournamentsWorksCorrectly() {
        //given

        List<TournamentEntity> allTournaments = tournamentService.findAllTournaments();
//when then
        Assertions.assertInstanceOf(List.class, allTournaments);
        Assertions.assertNotNull(allTournaments);


    }

    @Test
    void findTournamentByIdWorksCorrectly() {
        //given
        //when
        TournamentEntity tournamentById = tournamentService.findTournamentById(1);

        //then
        Assertions.assertNotNull(tournamentById);
        assertEquals("Bardzka piateczka", tournamentById.getNameOfTournament());

    }

    @Test
    void updateStartNumbersWorksCorrectly() {
//        given
//        when
        tournamentService.updateStartNumbers(1, 200);
        TournamentEntity tournamentById = tournamentService.findTournamentById(1);

//        then
        Assertions.assertEquals(200, tournamentById.getNumberOfStartPlates());


    }

    //
    @Test
    void updateDateOfTournament() {
//        given
//        when
        tournamentService.updateDateOfTournament(1, "08-05-1983", "08-05-1983");
        TournamentEntity tournamentById = tournamentService.findTournamentById(1);
//        then
        Assertions.assertEquals("08-05-1983", tournamentById.getStartOfTournament());

    }

    @Test
    void setTimeOfStart() {
//        given
        TournamentEntity tournamentById = tournamentService.findTournamentById(1);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime timeNow = LocalDateTime.now();
        String timeOfStart = formatter.format(timeNow);
        tournamentById.setStartOfCompetitors(timeOfStart);
//        when
        tournamentService.setTimeOfStart(tournamentById);
        TournamentEntity tournamentWithStartTime = tournamentService.findTournamentById(1);

//        then
        Assertions.assertEquals(timeOfStart,tournamentWithStartTime.getStartOfCompetitors());


    }
}