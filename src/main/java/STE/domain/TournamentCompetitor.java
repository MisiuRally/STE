package STE.domain;

import lombok.Builder;
import lombok.ToString;
import lombok.Value;
import lombok.With;

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
