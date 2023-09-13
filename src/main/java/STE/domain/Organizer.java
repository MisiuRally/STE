package STE.domain;

import lombok.Builder;
import lombok.Value;
import lombok.With;

@With
@Builder
@Value
//@EqualsAndHashCode(of = "")
//@ToString(of = {""})
public class Organizer {

    Integer organizerId;
    String nameOfOrganizer;
    String email;
    String phone;
    Address address;



}
