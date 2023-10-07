package STE.service;

import STE.infrastructure.database.entity.PersonEntity;
import STE.service.managment.ObjectsCreators;
import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AllArgsConstructor(onConstructor = @__(@Autowired))
class PersonServiceTestWorksCorrectly {

    private final ObjectsCreators objectsCreators;
    private final PersonService personService;


    @Test
    void savePerson() {
//        given
        PersonEntity person = objectsCreators.createPerson();
        int sizeBeforeAddPerson = personService.findAllPerson().size();
//        when
        personService.savePerson(person);
        int sizeAfterAddPerson = personService.findAllPerson().size();
//        then
        assertEquals(sizeBeforeAddPerson + 1, sizeAfterAddPerson);
    }

    @Test
    void findPersonByEmail() {
//        given
        PersonEntity person = objectsCreators.createPerson();
        personService.savePerson(person);

//        when
        PersonEntity personByEmail = personService.findPersonByEmail(person.getEmail());
//        then
        assertEquals(person.getEmail(), personByEmail.getEmail());
    }

    @Test
    void findAllPerson() {
//        given
//        when
        List<PersonEntity> allPerson = personService.findAllPerson();
//        then
        Assertions.assertInstanceOf(List.class, allPerson);
        Assertions.assertNotNull(allPerson);
        Assertions.assertInstanceOf(PersonEntity.class, allPerson.get(0));
    }

    @Test
    void update() {

//        given
        PersonEntity person = objectsCreators.createPerson();
        person.setPhone("123321");
//        when

        personService.update(person);
        PersonEntity personAfterUpdate = personService.findPersonByEmail(person.getEmail());
//        then
        assertEquals("123321",personAfterUpdate.getPhone());
    }
}