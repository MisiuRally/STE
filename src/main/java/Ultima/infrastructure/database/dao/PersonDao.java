package Ultima.infrastructure.database.dao;

import Ultima.domain.Person;
import Ultima.infrastructure.database.entity.PersonEntity;

import java.util.List;
import java.util.Optional;

public interface PersonDao {

        Optional<PersonEntity> findPersonByEmail(String email);

        void savePerson(PersonEntity person);

        List<PersonEntity> findAllPerson();


}
