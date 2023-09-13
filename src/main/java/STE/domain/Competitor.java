package STE.domain;

import lombok.Builder;
import lombok.Value;
import lombok.With;

@With
@Builder
@Value
//@EqualsAndHashCode(of = "")
//@ToString(of = {""})
public class Competitor {

    Integer competitorId;
    Person person;
    String ageCategories;
    Integer startNumber;
    String result;
    String averageSpeed;
    Tournament tournament;



}
