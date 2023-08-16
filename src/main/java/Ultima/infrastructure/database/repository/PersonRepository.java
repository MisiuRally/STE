package Ultima.infrastructure.database.repository;

import Ultima.domain.Person;
import Ultima.infrastructure.config.HibernateConfiguration;
import Ultima.infrastructure.database.dao.PersonDao;
import Ultima.infrastructure.database.entity.PersonEntity;
import Ultima.infrastructure.database.repository.jpa.PersonJpaRepository;
import Ultima.service.managment.ObjectsCreators;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Spliterator;

@AllArgsConstructor
@Slf4j
@Service
public class PersonRepository implements PersonDao {


    private final PersonJpaRepository personJpaRepository;

    @Override
    public Optional<PersonEntity> findPersonByEmail(String email) {
        return Optional.of(personJpaRepository.findPersonByEmail(email)) ;
    }

    @Override
    public void savePerson(PersonEntity person) {

//        try (
//                Session session = HibernateConfiguration.getSession()) {
//            if (Objects.isNull(session)) {
//                throw new RuntimeException("Session is null ");
//            }
//            session.beginTransaction();
//
//            session.persist(person);
//            session.getTransaction().commit();
//            log.info("Person saved");
//
//        }
        personJpaRepository.save(person);
    }

    @Override
    public List<PersonEntity> findAllPerson() {
        return personJpaRepository.findAll();

    }
}
