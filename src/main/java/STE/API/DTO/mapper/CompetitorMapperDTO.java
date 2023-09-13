package STE.API.DTO.mapper;

import STE.API.DTO.CompetitorDTO;
import STE.domain.Competitor;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CompetitorMapperDTO {

    private final PersonMapperDTO personMapperDTO;
    private final TournamentMapperDTO tournamentMapperDTO;

    public CompetitorDTO mapperToDTO(Competitor competitor){
        return CompetitorDTO.builder()
                .competitorId(competitor.getCompetitorId())
                .personDTO(personMapperDTO.mapperToDTO(competitor.getPerson()))
                .ageCategories(competitor.getAgeCategories())
                .startNumber(competitor.getStartNumber())
                .result(competitor.getResult())
                .averageSpeed(competitor.getAverageSpeed())
                .tournamentDTO(tournamentMapperDTO.mapperToDTO(competitor.getTournament()))
                .build();

    }

    public Competitor mapperFromDTO(CompetitorDTO competitorDTO){
        return Competitor.builder()
                .competitorId(competitorDTO.getCompetitorId())
                .person(personMapperDTO.mapperFromDTO(competitorDTO.getPersonDTO()))
                .ageCategories(competitorDTO.getAgeCategories())
                .startNumber(competitorDTO.getStartNumber())
                .result(competitorDTO.getResult())
                .averageSpeed(competitorDTO.getAverageSpeed())
                .tournament(tournamentMapperDTO.mapperFromDTO(competitorDTO.getTournamentDTO()))
                .build();

    }

}
