package STE.service;

import STE.infrastructure.database.dao.CompetitorDao;
import STE.infrastructure.database.dao.TournamentDao;
import STE.infrastructure.database.entity.CompetitorEntity;
import STE.service.managment.ObjectsCreators;
import STE.service.managment.Suppliers;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CompetitorServiceTest {
    @InjectMocks
    private CompetitorService competitorService;

    private ObjectsCreators objectsCreators;
    @Mock
    private CompetitorDao competitorDao;
    @Mock
    private Suppliers suppliers;



    @BeforeEach
    void setUp() {
        objectsCreators = new ObjectsCreators();

    }

    @Test
    void createAverageSpeed() {
//given
        CompetitorEntity competitorEntity = objectsCreators.createCompetitorEntity();
        Mockito.mock(Suppliers.class);
        Mockito.when(suppliers.localDateTimeSupplier()).thenReturn(LocalDateTime.of(2025,8,8,12,35));

        String result = competitorService.createAverageSpeed(competitorEntity);

        Assertions.assertEquals("260.8", result);
    }

    @Test
    void createStartNumberWorksCorrectly() {
        //given
        CompetitorEntity competitorEntity = objectsCreators.createCompetitorEntity();
        List<CompetitorEntity> list = new ArrayList<>();
        list.add(competitorEntity);
        Mockito.when(competitorDao.findAllCompetitorsWithTournamentId(1))
                .thenReturn(list);

        //when
        Integer result = competitorService.createStartNumber(1);

        //then
        Assertions.assertEquals(2, result);

    }
}