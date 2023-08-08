package Ultima.service.managment;

import Ultima.infrastructure.database.entity.AddressEntity;
import Ultima.infrastructure.database.entity.OrganizerEntity;
import Ultima.infrastructure.database.entity.PersonEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class ObjectsCreators {


    public PersonEntity createPerson(){
        return PersonEntity.builder()

                .build();

    }

    public OrganizerEntity createOrganizer(){
        return OrganizerEntity.builder()

                .build();

    }


}

