package STE.API.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TimeResultTournamentDTO {

    TimeResultDTO timeResultDTO;
    TournamentDTO tournamentDTO;
}
