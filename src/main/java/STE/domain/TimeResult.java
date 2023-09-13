package STE.domain;

import lombok.Builder;
import lombok.Value;
import lombok.With;

@With
@Builder
@Value
//@EqualsAndHashCode(of = "")
//@ToString(of = {""})
public class TimeResult {

    Integer resultOfTournamentId;
    String timeResult;
    Competitor competitor;
}
