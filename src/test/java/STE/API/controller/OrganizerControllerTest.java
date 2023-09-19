package STE.API.controller;

import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.LinkedMultiValueMap;

import static STE.API.controller.OrganizerController.ORGANIZER;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
@AllArgsConstructor(onConstructor = @__(@Autowired))
class OrganizerControllerTest {
    private final MockMvc mockMvc;

    @Test
    void organizerPageStartCorrectly() throws Exception {
        mockMvc.perform(get(ORGANIZER))
                .andExpect(status().isOk())
                .andExpect(view().name("organizer"));
    }

    @Test
    void showOrganizerTournamentsWorksCorrectly() throws Exception {
        //given
        LinkedMultiValueMap<String, String> parameters = new LinkedMultiValueMap<>();
        parameters.add("email", "bardo@bardo.com");

//when then
        mockMvc.perform(post(ORGANIZER + "/orgTour").params(parameters))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("availableOrganizerTournaments"));

    }

    @Test
    void showStartListWorksCorrectly() throws Exception {
        //given
        LinkedMultiValueMap<String, String> parameters = new LinkedMultiValueMap<>();
        parameters.add("tournamentId", "1");
        //when then

        mockMvc.perform(post(ORGANIZER + "/startList").params(parameters))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("startList"))
                .andReturn();

    }

}
