package Ultima.API.DTO;

import Ultima.domain.Tournament;
import Ultima.service.managment.Categories;
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
    TournamentDTO tournamentDTO;



}
