package STE.domain;

import lombok.Builder;
import lombok.Value;
import lombok.With;

@With
@Builder
@Value
//@EqualsAndHashCode(of = "")
//@ToString(of = {""})
public class Address {

    Integer addressId;
    String country;
    String city;
    String postalCode;
    String street;
}
