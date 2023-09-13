package STE.API.controller;

import STE.infrastructure.config.BeanConfiguration;
import STE.infrastructure.database.entity.OrganizerEntity;
import STE.infrastructure.database.entity.UserEntity;
import STE.service.OrganizerService;
import STE.service.managment.ObjectsCreators;
import lombok.AllArgsConstructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.LinkedMultiValueMap;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
@TestPropertySource(locations = "classpath:application-test.yml")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
@AllArgsConstructor(onConstructor = @__(@Autowired))
class AdministratorControllerTest {


    private MockMvc mockMvc;
    private ObjectsCreators objectsCreators;



    @Order(1)
    @BeforeEach
    public void beforeEach() {
        BeanConfiguration.cleanMigrateStrategy();
    }

    @Order(2)
    @Test
    void administratorWorksCorrectly() throws Exception {
        // given, when, then
        mockMvc.perform(get(AdministratorController.ADMINISTRATOR))
                .andExpect(status().isOk())
                .andExpect(view().name("administrator"));
    }

//    @Order(3)
//    @Test
//    void addNewOrganizerCorrectly() throws Exception {
//        //given
//        LinkedMultiValueMap<String, String> parameters = new LinkedMultiValueMap<>();
//        objectsCreators.mapToTestAddNewOrganizer().forEach(parameters::add);
//
//        //when//then
//        mockMvc.perform(post("/administrator/addNewOrganizer").params(parameters))
//                .andExpect(status().isOk());
//
//
//    }




}