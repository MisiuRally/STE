package Ultima.domain;

import Ultima.service.managment.Categories;
import lombok.*;

@With
@Builder
@Value
@EqualsAndHashCode(of = "")
@ToString(of = {""})
public class Competitor {

    Integer competitorId;
    Person person;
    Categories.AgeCategories ageCategories;
    Integer startNumber;



}
