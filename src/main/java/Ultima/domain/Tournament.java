package Ultima.domain;

import Ultima.service.managment.Categories;
import lombok.*;

import java.time.OffsetDateTime;
import java.util.Set;

@With
@Builder
@Value
@EqualsAndHashCode(of = "")
@ToString(of = {""})
public class Tournament {

    Integer integerId;
    String namOfTournament;
    Integer numbersOfStartPlates;
    Organizer organizer;
    Categories.SportCategories sportCategories;
    OffsetDateTime startOfTournament;
    OffsetDateTime endOfTournament;
    TimeResult result;
    Set<Competitor> startList;

}
