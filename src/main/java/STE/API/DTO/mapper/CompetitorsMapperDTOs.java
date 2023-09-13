package STE.API.DTO.mapper;

import STE.API.DTO.CompetitorsDTOs;
import STE.domain.Tournament;
import STE.infrastructure.database.entity.CompetitorEntity;
import STE.infrastructure.database.repository.mapper.TournamentEntityMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CompetitorsMapperDTOs {
    private final TournamentEntityMapper tournamentEntityMapper;
    private final TournamentMapperDTO tournamentMapperDTO;

    public CompetitorsDTOs mapperToDTO(CompetitorEntity competitorEntity) {
        Tournament tournament = tournamentEntityMapper.mapperFromEntity(competitorEntity.getTournamentEntity());

        return CompetitorsDTOs.builder()
                .startNumber(competitorEntity.getStartNumber())
                .name(competitorEntity.getPerson().getName())
                .surname(competitorEntity.getPerson().getSurname())
                .ageCategories(competitorEntity.getAgeCategories())
                .city(competitorEntity.getPerson().getAddress().getCity())
                .result(competitorEntity.getResult())
                .averageSpeed(competitorEntity.getAverageSpeed())
                .place(competitorEntity.getPlace())
                .tournamentDTO(tournamentMapperDTO.mapperToDTO(tournament))
                .build();
    }

}
