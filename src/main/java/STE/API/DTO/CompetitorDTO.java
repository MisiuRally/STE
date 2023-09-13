package STE.API.DTO;

import lombok.*;

@Data
@With
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CompetitorDTO {

    Integer competitorId;
    PersonDTO personDTO;
    String ageCategories;
    Integer startNumber;
    String result;
    String averageSpeed;
    TournamentDTO tournamentDTO;



}
