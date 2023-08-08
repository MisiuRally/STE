package Ultima.API.DTO.mapper;

import Ultima.API.DTO.CompetitorDTO;
import Ultima.API.DTO.TournamentDTO;
import Ultima.domain.Competitor;
import Ultima.domain.Tournament;
import Ultima.infrastructure.database.entity.TournamentEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class TournamentMapperDTO {
    private final OrganizerMapperDTO organizerMapperDTO;

    public TournamentDTO mapperToDTO (Tournament tournament){
       return TournamentDTO.builder()
               .tournamentId(tournament.getTournamentId())
               .namOfTournament(tournament.getNamOfTournament())
               .numbersOfStartPlates(tournament.getNumbersOfStartPlates())
               .organizerDTO(organizerMapperDTO.mapperToDTO(tournament.getOrganizer()))
               .sportCategories(tournament.getSportCategories())
               .startOfTournament(tournament.getStartOfTournament())
               .endOfTournament(tournament.getEndOfTournament())
                .build();
    }


   public Tournament mapperFromDTO(TournamentDTO tournamentDTO){
        return Tournament.builder()
                .tournamentId(tournamentDTO.getTournamentId())
                .namOfTournament(tournamentDTO.getNamOfTournament())
                .numbersOfStartPlates(tournamentDTO.getNumbersOfStartPlates())
                .organizer(organizerMapperDTO.mapperFromDto(tournamentDTO.getOrganizerDTO()))
                .sportCategories(tournamentDTO.getSportCategories())
                .startOfTournament(tournamentDTO.getStartOfTournament())
                .endOfTournament(tournamentDTO.getEndOfTournament())
                .build();
    }

}
