package Ultima.infrastructure.database.repository.mapper;

import Ultima.domain.Address;
import Ultima.domain.Competitor;
import Ultima.domain.Person;
import Ultima.infrastructure.database.entity.AddressEntity;
import Ultima.infrastructure.database.entity.CompetitorEntity;
import Ultima.infrastructure.database.entity.PersonEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
@AllArgsConstructor
public class CompetitorEntityMapper {

    private final PersonEntityMapper personEntityMapper;
    private final TournamentEntityMapper tournamentEntityMapper;

    public Competitor mapperFromEntity(CompetitorEntity competitorEntity) {
        return Competitor.builder()
                .competitorId(competitorEntity.getCompetitorId())
                .person(personEntityMapper.mapperFromEntity(competitorEntity.getPerson()))
                .ageCategories(competitorEntity.getAgeCategories())
                .startNumber(competitorEntity.getStartNumber())
                .result(competitorEntity.getResult())
                .averageSpeed(competitorEntity.getAverageSpeed())
                .tournament(tournamentEntityMapper.mapperFromEntity(competitorEntity.getTournamentEntity()))
                .build();
    }

    public CompetitorEntity mapperToEntity(Competitor competitor){
        return CompetitorEntity.builder()
                .competitorId(competitor.getCompetitorId())
                .person(personEntityMapper.mapperToEntity(competitor.getPerson()))
                .ageCategories(competitor.getAgeCategories())
                .startNumber(competitor.getStartNumber())
                .result(competitor.getResult())
                .averageSpeed(competitor.getAverageSpeed())
                .tournamentEntity(tournamentEntityMapper.mapperToEntity(competitor.getTournament()))
                .build();
    }
}
