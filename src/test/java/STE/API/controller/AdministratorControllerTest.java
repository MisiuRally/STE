package STE.API.controller;

import STE.infrastructure.config.BeanConfiguration;
import STE.service.managment.ObjectsCreators;
import lombok.AllArgsConstructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
@TestPropertySource(locations = "classpath:application-test.yml")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
@AllArgsConstructor(onConstructor = @__(@Autowired))
class AdministratorControllerTest {


    private MockMvc mockMvc;

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


}