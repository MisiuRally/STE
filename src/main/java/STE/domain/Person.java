package STE.domain;

import lombok.Builder;
import lombok.Value;
import lombok.With;

@With
@Builder
@Value
//@EqualsAndHashCode(of = "")
//@ToString(of = {""})
public class Person {

    Integer personId;
    String name;
    String surname;
    String dateOfBirth;
    String email;
    String phone;
    String sex;
    Address address;
}
