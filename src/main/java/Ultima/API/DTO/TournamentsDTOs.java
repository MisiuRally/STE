package Ultima.API.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;
import java.util.Set;

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
    OffsetDateTime startOfTournament;
    OffsetDateTime endOfTournament;


}
