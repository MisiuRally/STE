package Ultima.domain;

import Ultima.service.managment.Categories;
import lombok.*;

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
    OffsetDateTime startOfTournament;
    OffsetDateTime endOfTournament;
    Set<Competitor> startList;

}
