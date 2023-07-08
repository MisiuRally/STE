package Ultima.service;

import Ultima.domain.Address;
import Ultima.domain.Person;
import Ultima.service.managment.Categories;
import Ultima.service.managment.InputData;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
public class PersonService {

    private final Person person;
    private final InputData inputData;

    public Person personCreator(InputData inputData ){

       return Person.builder()
                .name("Michal")
                .surname("Kasperek")
                .sex(Categories.Sex.MALE)
                .email("misiurally83@gmail.com")
                .phone("504665183")
                .address(Address.builder()
                        .city("Swidnica")
                        .country("Poland")
                        .postalCode("58-100")
                        .street("Lipowa8")
                        .build()
                ).build();
    }


}
