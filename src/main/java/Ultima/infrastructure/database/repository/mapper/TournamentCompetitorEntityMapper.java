package Ultima.infrastructure.database.repository.mapper;

import Ultima.domain.*;
import Ultima.infrastructure.database.entity.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class TournamentCompetitorEntityMapper {
private final TournamentEntityMapper tournamentEntityMapper;
private final CompetitorEntityMapper competitorEntityMapper;

    public TournamentCompetitorEntity mapperToEntity(TournamentCompetitor tournamentCompetitor) {
        return TournamentCompetitorEntity.builder()
//                .tournamentCompetitorId(tournamentCompetitor.getTournamentCompetitorId())
//                .tournament(tournamentEntityMapper.mapperToEntity(tournamentCompetitor.getTournament()))
//                .competitorEntities(competitorEntityMapper.mapperToEntity(tournamentCompetitor.getCompetitor()))
                .build();

    }

    public TournamentCompetitor mapperFromEntity(TournamentCompetitorEntity tournamentCompetitorEntity) {
        return TournamentCompetitor.builder()
//                .tournamentCompetitorId(tournamentCompetitorEntity.getTournamentCompetitorId())
//                .tournament(tournamentEntityMapper.mapperFromEntity(tournamentCompetitorEntity.getTournament()))
//                .competitor(competitorEntityMapper.mapperFromEntity(tournamentCompetitorEntity.getCompetitorEntities()))
                .build();

    }

//    private Set<CompetitorEntity> prepareSetOfCompetitorsEntities(TournamentCompetitor tournamentCompetitor){
//        Set<CompetitorEntity> competitor = tournamentCompetitor.getCompetitor().stream()
//                .map(competitorEntityMapper::mapperToEntity)
//                .collect(Collectors.toSet());
//        return competitor;
//    }
//
//    private Set<Competitor> prepareSetOfCompetitorsEntities(TournamentCompetitorEntity tournamentCompetitorEntity){
//        Set<Competitor> competitor = tournamentCompetitorEntity.getCompetitorEntities().stream()
//                .map(competitorEntityMapper::mapperFromEntity)
//                .collect(Collectors.toSet());
//        return competitor;
//    }




}
