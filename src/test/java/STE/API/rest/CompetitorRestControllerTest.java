package STE.API.rest;

import STE.infrastructure.database.entity.CompetitorEntity;
import STE.infrastructure.database.entity.PersonEntity;
import STE.infrastructure.database.repository.CompetitorRepository;
import STE.service.CompetitorService;
import STE.service.PersonService;
import STE.service.managment.ObjectsCreators;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Slf4j
@ActiveProfiles("test")
@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
@AllArgsConstructor(onConstructor = @__(@Autowired))
class CompetitorRestControllerTest {

    private final CompetitorService competitorService;
    private final PersonService personService;
    private final CompetitorRepository competitorRepository;

    private final MockMvc mockMvc;


    ObjectsCreators objectsCreators;


    @BeforeEach
    void setUp() {
        this.objectsCreators = new ObjectsCreators();

    }

    @Test
    void competitorHistory() throws Exception {
        //given
        PersonEntity person = objectsCreators.createPerson();
        personService.savePerson(person);
        competitorService.addExistingCompetitorToTournament(person.getEmail(), 1);
        //when then
        mockMvc.perform(get("/apiCompetitor/history/{email}", "jurek@wp.pl"))
                .andExpect(status().isOk());
    }

    @Test
    void deleteCompetitor() throws Exception {
        //given
        List<CompetitorEntity> allCompetitorsWithTournamentId = competitorService.findAllCompetitorsWithTournamentId(1);
        if(allCompetitorsWithTournamentId.isEmpty()){
            PersonEntity person = objectsCreators.createPerson();
            personService.savePerson(person);
            competitorService.addExistingCompetitorToTournament(person.getEmail(), 1);

        }else
            //when then
        mockMvc.perform(delete("/apiCompetitor/delete/{id}", 1))
                .andExpect(status().isOk());
    }
}