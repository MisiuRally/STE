package STE.API.controller;

import STE.infrastructure.config.BeanConfiguration;
import lombok.AllArgsConstructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;


@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
@AllArgsConstructor(onConstructor = @__(@Autowired))
class HomeControllerTest {

    private MockMvc mockMvc;

    @BeforeEach
    public void beforeAll() {
        BeanConfiguration.cleanMigrateStrategy();
    }

    @Test
    void homeWorksCorrectly() throws Exception {
        // given, when, then
        mockMvc.perform(get(HomeController.HOME))
                .andExpect(status().isOk())
                .andExpect(view().name("home"));
    }

}