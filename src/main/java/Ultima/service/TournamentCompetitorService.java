package Ultima.service;

import Ultima.infrastructure.database.dao.CompetitorDao;
import Ultima.infrastructure.database.dao.TournamentDao;
import Ultima.infrastructure.database.entity.CompetitorEntity;
import Ultima.infrastructure.database.entity.TournamentCompetitorEntity;
import Ultima.infrastructure.database.entity.TournamentEntity;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Slf4j
@Service
@AllArgsConstructor
public class TournamentCompetitorService {


    public TournamentCompetitorEntity createTournamentCompetitor(TournamentEntity tournamentEntity, CompetitorEntity competitorEntity) {
        Set<CompetitorEntity> setOfCompetitors = new HashSet<>();
        setOfCompetitors.add(competitorEntity);

        return TournamentCompetitorEntity.builder()
                .tournamentEntity(tournamentEntity)
                .build().withCompetitorEntities(setOfCompetitors);

    }


}
