package Ultima.service;

import Ultima.API.DTO.AddressDTO;
import Ultima.API.DTO.PersonDTO;
import Ultima.API.DTO.mapper.PersonMapperDTO;
import Ultima.domain.Address;
import Ultima.domain.Person;
import Ultima.infrastructure.database.dao.PersonDao;
import Ultima.infrastructure.database.entity.PersonEntity;
import Ultima.infrastructure.database.repository.mapper.PersonEntityMapper;
import Ultima.service.managment.Categories;
import Ultima.service.managment.InputData;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
public class PersonService {

    private final PersonDao personDao;
    private final PersonEntityMapper personEntityMapper;
    private final PersonMapperDTO personMapperDTO;




    public PersonDTO personBuilder(String name, String surname, String dateOfBirth, String email, String phone, String sex, String country, String city, String postalCode, String street) {
        return PersonDTO.builder()
                .name(name)
                .surname(surname)
                .email(email)
                .phone(phone)
                .sex(sex)
                .dateOfBirth(dateOfBirth)
                .address(AddressDTO.builder()
                        .country(country)
                        .city(city)
                        .postalCode(postalCode)
                        .street(street)
                        .build())
                .build();
    }

    public void savePerson(PersonDTO personDTO) {
        Person person = personMapperDTO.mapperFromDTO(personDTO);
        PersonEntity personEntity = personEntityMapper.mapperToEntity(person);
        personDao.savePerson(personEntity);
    }

    public PersonEntity findPersonByEmail(String email) {
        return personDao.findPersonByEmail(email).get();
    }
}
