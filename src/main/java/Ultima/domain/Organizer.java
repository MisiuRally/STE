package Ultima.domain;

import lombok.*;

import java.util.Set;

@With
@Builder
@Value
@EqualsAndHashCode(of = "")
@ToString(of = {""})
public class Organizer {

    Integer organizerId;
    String nameOfOrganizer;
    String email;
    String phone;
    Address address;



}
