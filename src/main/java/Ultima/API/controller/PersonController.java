package Ultima.API.controller;

import Ultima.API.DTO.AddressDTO;
import Ultima.API.DTO.PersonDTO;
import Ultima.service.PersonService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(PersonController.PERSON)
@AllArgsConstructor
public class PersonController {

    public static final String PERSON = "/person";


    private final PersonService personService;

    @PostMapping("/add_person")
    public PersonDTO  createPersonDTO(
            @RequestParam(value = "name") String name,
            @RequestParam(value = "surname") String surname,
            @RequestParam(value = "dateOfBirth") String dateOfBirth,
            @RequestParam(value = "email") String email,
            @RequestParam(value = "phone") String phone,
            @RequestParam(value = "sex") String sex,
            @RequestParam(value = "country") String country,
            @RequestParam(value = "city") String city,
            @RequestParam(value = "postalCode") String postalCode,
            @RequestParam(value = "street") String street
    ) {
        PersonDTO personDTO = PersonDTO.builder()
                .name(name)
                .surname(surname)
                .dateOfBirth(dateOfBirth)
                .email(email)
                .phone(phone)
                .sex(sex)
                .address(AddressDTO.builder()
                        .country(country)
                        .city(city)
                        .postalCode(postalCode)
                        .street(street)
                        .build())
                .build();



        return personDTO;

    }


}
