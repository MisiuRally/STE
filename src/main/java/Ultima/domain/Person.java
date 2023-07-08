package Ultima.domain;

import Ultima.service.managment.Categories;
import lombok.*;

import java.time.LocalDateTime;

@With
@Builder
@Value
@EqualsAndHashCode(of = "")
@ToString(of = {""})
public class Person {
    Integer personId;
    String name;
    String surname;
    LocalDateTime dateOfBirth;
    String email;
    String phone;
    Categories.Sex sex;
    Address address;
}
