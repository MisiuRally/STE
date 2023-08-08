package Ultima.API.DTO.mapper;

import Ultima.API.DTO.AddressDTO;
import Ultima.API.DTO.PersonDTO;
import Ultima.domain.Address;
import Ultima.domain.Person;
import org.springframework.stereotype.Component;

@Component
public class PersonMapperDTO {

    public Person mapperFromDTO(PersonDTO personDTO){
        return Person.builder()
                .name(personDTO.getName())
                .surname(personDTO.getSurname())
                .phone(personDTO.getPhone())
                .email(personDTO.getEmail())
                .sex(personDTO.getSex())
                .dateOfBirth(personDTO.getDateOfBirth())
                .address(Address.builder()
                        .country(personDTO.getAddress().getCountry())
                        .city(personDTO.getAddress().getCity())
                        .postalCode(personDTO.getAddress().getPostalCode())
                        .street(personDTO.getAddress().getStreet())
                        .build())
                .build();

    }

    public PersonDTO mapperToDTO(Person person){
        return PersonDTO.builder()
                .name(person.getName())
                .surname(person.getSurname())
                .phone(person.getPhone())
                .email(person.getEmail())
                .sex(person.getSex())
                .dateOfBirth(person.getDateOfBirth())
                .address(AddressDTO.builder()
                        .country(person.getAddress().getCountry())
                        .city(person.getAddress().getCity())
                        .postalCode(person.getAddress().getPostalCode())
                        .street(person.getAddress().getStreet())
                        .build())
                .build();

    }
}
