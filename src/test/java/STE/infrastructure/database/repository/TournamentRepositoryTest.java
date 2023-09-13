package STE.infrastructure.database.repository;

import STE.infrastructure.database.entity.TournamentEntity;
import STE.infrastructure.database.repository.jpa.TournamentJpaRepository;
import STE.service.managment.ObjectsCreators;
import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class TournamentRepositoryTest {
    @Mock
    private TournamentJpaRepository tournamentJpaRepository;
    private ObjectsCreators objectsCreators;
    @InjectMocks
    private TournamentRepository tournamentRepositoryToTest;

    @BeforeEach
    void setUp() {
        tournamentRepositoryToTest = new TournamentRepository(tournamentJpaRepository);
        objectsCreators = new ObjectsCreators();
    }

    @Test
    void findAllTournamentWorksCorrectly() {
        //given when then
        tournamentRepositoryToTest.findAllTournament();
        verify(tournamentJpaRepository).findAll();
    }

    @Test
    void findTournamentByIdWorksCorrectly() {
        //given
        TournamentEntity tournamentEntity = objectsCreators.createTournamentEntity();
        when(tournamentJpaRepository.findById(1))
                .thenReturn(Optional.of(tournamentEntity));

        //when
        TournamentEntity result = tournamentRepositoryToTest.findTournamentById(1);
        //then
        Assertions.assertEquals(tournamentEntity,result);

    }

    @Test
    void findTournamentByIdThrowExceptionCorrectly(){
        //given
        when(tournamentJpaRepository.findById(1))
                .thenReturn(Optional.empty());

        //when then
        assertThatThrownBy(()->tournamentRepositoryToTest.findTournamentById(1))
                .hasMessage("Tournament with id: [%s] does not exist".formatted(1));

    }

    @Test
    void saveWorksCorrectly() {
//given
        TournamentEntity tournamentEntity = objectsCreators.createTournamentEntity();
// when then
        tournamentRepositoryToTest.save(tournamentEntity);
        verify(tournamentJpaRepository).saveAndFlush(tournamentEntity);

    }
}