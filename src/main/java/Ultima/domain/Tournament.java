package Ultima.domain;

import Ultima.service.managment.Categories;
import lombok.*;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.Set;

@With
@Builder
@Value
@Setter
//@EqualsAndHashCode(of = "")
//@ToString(of = {""})
public class Tournament {

    Integer tournamentId;
    String namOfTournament;
    Integer numbersOfStartPlates;
    Organizer organizer;
    String sportCategories;
    String startOfTournament;
    String endOfTournament;
    Integer distance;
    BigDecimal buyIn;
    String startOfCompetitors;
    Set<Competitor> startList;

}
