package Ultima.domain;

import lombok.*;

@With
@Builder
@Value
@EqualsAndHashCode(of = "")
@ToString(of = {""})
public class Address {

    Integer addressId;
    String country;
    String city;
    String postalCode;
    String street;
}
