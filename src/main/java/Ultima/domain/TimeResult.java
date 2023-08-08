package Ultima.domain;

import lombok.*;

import java.util.Map;

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
