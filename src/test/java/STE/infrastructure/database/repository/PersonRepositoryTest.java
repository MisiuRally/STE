package STE.infrastructure.database.repository;

import STE.infrastructure.database.entity.CompetitorEntity;
import STE.infrastructure.database.entity.PersonEntity;
import STE.infrastructure.database.repository.jpa.PersonJpaRepository;
import STE.service.managment.ObjectsCreators;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class PersonRepositoryTest {
    @InjectMocks
    private PersonRepository personRepository;
    @Mock
    private PersonJpaRepository personJpaRepository;
    private ObjectsCreators objectsCreators;

    @BeforeEach
    void setUp() {
        objectsCreators = new ObjectsCreators();
        personRepository = new PersonRepository(personJpaRepository);

    }

    @Test
    void findPersonByEmail() {
        //given
        PersonEntity person = objectsCreators.createPerson();
        Mockito.when(personJpaRepository.findPersonByEmail(person.getEmail()))
                .thenReturn(person);

        //when
        Optional<PersonEntity> result = personRepository.findPersonByEmail(person.getEmail());
        //then
        Assertions.assertEquals(person.getPersonId(), result.get().getPersonId());
    }

    @Test
    void savePerson() {
        //given
        PersonEntity person = objectsCreators.createPerson();
//when then
        personRepository.savePerson(person);
        verify(personJpaRepository).save(person);
    }

    @Test
    void findAllPerson() {
        //given
        PersonEntity person = objectsCreators.createPerson();

        List<PersonEntity> list = new ArrayList<>();
        list.add(person);

        Mockito.when(personJpaRepository.findAll()).thenReturn(list);

        //when
        List<PersonEntity> allPerson = personRepository.findAllPerson();
        boolean size= allPerson.size()>0;

        //then
        Assertions.assertTrue(size);

    }
}