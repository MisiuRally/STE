package Ultima.domain;

import lombok.*;

import java.util.Set;

@With
@Builder
@Value
//@EqualsAndHashCode(of = "integerId")
@ToString(of = {"tournament","competitor"})
public class TournamentCompetitor {

    Integer tournamentCompetitorId;
    Tournament tournament;
    Competitor competitor;
}
