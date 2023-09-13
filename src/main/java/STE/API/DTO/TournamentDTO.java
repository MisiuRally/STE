package STE.API.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TournamentDTO {

    Integer tournamentId;
    String namOfTournament;
    Integer numbersOfStartPlates;
    OrganizerDTO organizerDTO;
    String sportCategories;
    String startOfTournament;
    String endOfTournament;
    Integer distance;
    BigDecimal buyIn;
    String startOfCompetitors;
    Set<CompetitorDTO> competitorDTOS;

}
