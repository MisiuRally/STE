package Ultima.domain;

import Ultima.service.managment.Categories;
import lombok.*;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

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
