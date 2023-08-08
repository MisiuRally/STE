package Ultima.infrastructure.database.repository.mapper;

import Ultima.domain.Address;
import Ultima.domain.Person;
import Ultima.infrastructure.database.entity.AddressEntity;
import Ultima.infrastructure.database.entity.PersonEntity;
import Ultima.service.managment.Categories;
import org.springframework.stereotype.Component;

@Component
public class PersonEntityMapper {

    public Person mapperFromEntity(PersonEntity personEntity){
        return Person.builder()
                .personId(personEntity.getPersonId())
                .name(personEntity.getName())
                .surname(personEntity.getSurname())
                .phone(personEntity.getPhone())
                .email(personEntity.getEmail())
                .dateOfBirth(personEntity.getDateOfBirth())
                .sex(personEntity.getSex())
                .address(Address.builder()
                        .addressId(personEntity.getAddress().getAddressId())
                        .country(personEntity.getAddress().getCountry())
                        .city(personEntity.getAddress().getCity())
                        .postalCode(personEntity.getAddress().getPostalCode())
                        .street(personEntity.getAddress().getStreet())
                        .build())
                .build();
    }

    public PersonEntity mapperToEntity(Person person){
        return PersonEntity.builder()
                .personId(person.getPersonId())
                .name(person.getName())
                .surname(person.getSurname())
                .phone(person.getPhone())
                .email(person.getEmail())
                .dateOfBirth(person.getDateOfBirth())
                .sex(person.getSex())
                .address(AddressEntity.builder()
                        .addressId(person.getAddress().getAddressId())
                        .country(person.getAddress().getCountry())
                        .city(person.getAddress().getCity())
                        .postalCode(person.getAddress().getPostalCode())
                        .street(person.getAddress().getStreet())
                        .build())
                .build();
    }
}
