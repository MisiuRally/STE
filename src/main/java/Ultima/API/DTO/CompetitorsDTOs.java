package Ultima.API.DTO;

import lombok.*;

@Data
@With
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CompetitorsDTOs {

    Integer startNumber;
    String name;
    String surname;
    String ageCategories;
    String city;
    String result;
    String averageSpeed;
    TournamentDTO tournamentDTO;


}
