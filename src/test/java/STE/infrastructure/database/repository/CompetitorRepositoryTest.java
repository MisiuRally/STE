package STE.infrastructure.database.repository;

import STE.infrastructure.database.entity.CompetitorEntity;
import STE.infrastructure.database.entity.TournamentEntity;
import STE.infrastructure.database.repository.jpa.CompetitorJpaRepository;
import STE.service.managment.ObjectsCreators;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class CompetitorRepositoryTest {
    @Mock
    private CompetitorJpaRepository competitorJpaRepository;
    @InjectMocks
    private CompetitorRepository competitorRepository;
    private ObjectsCreators objectsCreators;


    @BeforeEach
    void setUp() {
        competitorRepository = new CompetitorRepository(competitorJpaRepository);
        objectsCreators = new ObjectsCreators();

    }

    @Test
    void saveCompetitorWorksCorrectly() {
        //given
        CompetitorEntity competitorEntity = objectsCreators.createCompetitorEntity();
//when then
        competitorRepository.saveCompetitor(competitorEntity);
        verify(competitorJpaRepository).saveAndFlush(competitorEntity);
    }

    @Test
    void findAllCopmetitorsWorksCorrectly() {
        //given

        List<CompetitorEntity> list = new ArrayList<>();
        list.add(objectsCreators.createCompetitorEntity());
        Mockito.when(competitorRepository.findAllCopmetitors())
                .thenReturn(list);
        //when
        List<CompetitorEntity> all = competitorJpaRepository.findAll();
        //then
        Assertions.assertEquals(1, all.size());

    }

    @Test
    void findCompetitorByEmailWorksCorrectly() {
        //given
        CompetitorEntity competitorEntity = objectsCreators.createCompetitorEntity();
        Mockito.when(competitorJpaRepository.findCompetitorByEmail(competitorEntity.getPerson().getEmail()))
                .thenReturn(Optional.of(competitorEntity));

        //when//then
        competitorRepository.findCompetitorByEmail(competitorEntity.getPerson().getEmail());
        verify(competitorJpaRepository).findCompetitorByEmail(competitorEntity.getPerson().getEmail());

    }

    @Test
    void findCompetitorThrowExceptionCorrectly() {
        //given
        CompetitorEntity competitorEntity = objectsCreators.createCompetitorEntity();
        Mockito.when(competitorJpaRepository.findCompetitorByEmail(competitorEntity.getPerson().getEmail()))
                .thenReturn(Optional.empty());

        //when then
        assertThatThrownBy(() -> competitorRepository.findCompetitorByEmail(competitorEntity.getPerson().getEmail()))
                .hasMessage("Competitor with email: [%s] does not exist".formatted(competitorEntity.getPerson().getEmail()));

    }

    @Test
    void findCompetitorByStartNumberAndTournamentIdThrowCorrectly() {
        //given
        CompetitorEntity competitorEntity = objectsCreators.createCompetitorEntity();
        TournamentEntity tournamentEntity = objectsCreators.createTournamentEntity();

        when(competitorJpaRepository.findCompetitorByStartNumberAndTournamentId(competitorEntity.getStartNumber().toString(), tournamentEntity.getTournamentId().toString()))
                .thenReturn(Optional.empty());


        assertThatThrownBy(() -> competitorRepository.findCompetitorByStartNumberAndTournamentId(competitorEntity.getStartNumber().toString(), tournamentEntity.getTournamentId().toString()))
                .hasMessage("Competitor with start number [%s] and torunament id[%s] does not exist!"
                        .formatted(competitorEntity.getStartNumber().toString(), tournamentEntity.getTournamentId().toString()));


    }


}