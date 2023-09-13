package STE.domain;

import lombok.Builder;
import lombok.Value;
import lombok.With;

@With
@Builder
@Value
//@EqualsAndHashCode(of = "")
//@ToString(of = {""})
public class TimeResultTournament {

    TimeResult timeResult;
    Tournament tournament;
}
