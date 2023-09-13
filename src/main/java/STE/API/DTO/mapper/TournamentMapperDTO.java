package STE.API.DTO.mapper;

import STE.API.DTO.TournamentDTO;
import STE.domain.Tournament;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

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
               .distance(tournament.getDistance())
               .buyIn(tournament.getBuyIn())
               .startOfCompetitors(tournament.getStartOfCompetitors())
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
                .distance(tournamentDTO.getDistance())
                .buyIn(tournamentDTO.getBuyIn())
                .startOfCompetitors(tournamentDTO.getStartOfCompetitors())
                .build();
    }

}
