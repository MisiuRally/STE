package STE.infrastructure.database.dao;

import STE.infrastructure.database.entity.PersonEntity;

import java.util.List;
import java.util.Optional;

public interface PersonDao {

        Optional<PersonEntity> findPersonByEmail(String email);

        void savePerson(PersonEntity person);

        List<PersonEntity> findAllPerson();


    void update(PersonEntity personByEmail);
}
