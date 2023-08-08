package Ultima.API.DTO;

import Ultima.service.managment.Categories;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PersonDTO {


    String name;
    String surname;
    String dateOfBirth;
    String email;
    String phone;
    String sex;
    AddressDTO address;
}
