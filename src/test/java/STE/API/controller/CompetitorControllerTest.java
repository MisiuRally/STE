package STE.API.controller;

import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
@AllArgsConstructor(onConstructor = @__(@Autowired))
class CompetitorControllerTest {
    private MockMvc mockMvc;
    @Test
    void competitorPageWorksStartCorrectly() throws Exception {
        // given, when, then
        mockMvc.perform(get(CompetitorController.COMPETITOR))
                .andExpect(status().isOk())
                .andExpect(view().name("competitor"))
                .andExpect(model().attributeExists("availableTournament"))
                .andExpect(view().name("competitor"));
    }
    }
