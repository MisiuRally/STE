package Ultima.API.DTO.mapper;

import Ultima.API.DTO.CompetitorsDTOs;
import Ultima.domain.Tournament;
import Ultima.infrastructure.database.entity.CompetitorEntity;
import Ultima.infrastructure.database.repository.mapper.TournamentEntityMapper;
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
                .tournamentDTO(tournamentMapperDTO.mapperToDTO(tournament))
                .build();
    }

}
