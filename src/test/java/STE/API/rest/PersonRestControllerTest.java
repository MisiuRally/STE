package STE.API.rest;

import STE.infrastructure.database.entity.PersonEntity;
import STE.service.PersonService;
import STE.service.managment.ObjectsCreators;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Slf4j
@ActiveProfiles("test")
@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
@AllArgsConstructor(onConstructor = @__(@Autowired))
class PersonRestControllerTest {

    private final ObjectsCreators objectsCreators;
    private final PersonService personService;
    private final MockMvc mockMvc;
    @Test
    void updateOfCompetitor() throws Exception {
//given
        PersonEntity person = objectsCreators.createPerson();
        personService.savePerson(person);


        //when then
        assertEquals("123887662", person.getPhone());

        mockMvc.perform(put("/apiPerson/update/{email}/{phone}", "jurek@wp.pl","555444"))
                .andExpect(status().isOk());
        PersonEntity personByEmail = personService.findPersonByEmail("jurek@wp.pl");
        assertEquals("555444", personByEmail.getPhone());
    }
}