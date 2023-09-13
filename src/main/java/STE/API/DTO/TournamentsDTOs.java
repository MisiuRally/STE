package STE.API.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TournamentsDTOs {

    Integer id;
    String namOfTournament;
    Integer numbersOfStartPlates;
    String nameOfOrganizer;
    String city;
    String sportCategories;
    String startOfTournament;
    String endOfTournament;
    Integer distance;
    String startOfCompetitors;
    BigDecimal buyIn;


}
