package STE.infrastructure.database.repository;

import STE.infrastructure.database.dao.PersonDao;
import STE.infrastructure.database.entity.PersonEntity;
import STE.infrastructure.database.repository.jpa.PersonJpaRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Slf4j
@Repository
@AllArgsConstructor
public class PersonRepository implements PersonDao {


    PersonJpaRepository personJpaRepository;

    @Override
    public Optional<PersonEntity> findPersonByEmail(String email) {
        return Optional.of(personJpaRepository.findPersonByEmail(email));
    }

    @Override
    public void savePerson(PersonEntity person) {
        personJpaRepository.save(person);
    }

    @Override
    public List<PersonEntity> findAllPerson() {
        return personJpaRepository.findAll();

    }

    @Override
    public void update(PersonEntity personByEmail) {
        personJpaRepository.saveAndFlush(personByEmail);
    }
}
