package Ultima.domain;

import lombok.*;

@With
@Builder
@Value
@EqualsAndHashCode(of = "")
@ToString(of = {""})
public class TimeResultTournament {

    TimeResult timeResult;
    Tournament tournament;
}
