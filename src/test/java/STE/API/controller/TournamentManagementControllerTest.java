package STE.API.controller;

import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
@AllArgsConstructor(onConstructor = @__(@Autowired))
class TournamentManagementControllerTest {

    private final MockMvc mockMvc;
    @Test
    void showResultOfTournamentWorksCorrectly() throws Exception {
        // given, when, then
        mockMvc.perform(get("/tournament_management/result").param("tournamentId","1"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("result"))
                .andExpect(model().attributeExists("tournamentDTO"))
                .andExpect(view().name("tournament_management"));

    }
}