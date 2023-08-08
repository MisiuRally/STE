package Ultima.domain;

import Ultima.service.managment.Categories;
import lombok.*;

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
    Tournament tournament;



}
