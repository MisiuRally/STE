package STE.service;

import STE.domain.Organizer;
import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.TestPropertySource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@AllArgsConstructor(onConstructor = @__(@Autowired))
class OrganizerServiceTest {

    private final OrganizerService organizerService;



    @Test
    void findOrganizerByEmail() {
        //given
        //when
        Organizer result = organizerService.findOrganizerByEmail("bardo@bardo.com");
        String nameOfOrganizer = result.getNameOfOrganizer();
        //then
        assertEquals("Gmina Bardo", nameOfOrganizer);
    }

    @Test
    void findAllOrganizer() {
        //given when
        List<Organizer> allOrganizer = organizerService.findAllOrganizer();
        int result = allOrganizer.size();
        //then
        assertEquals(3, result);
    }
}