package STE.service;

import STE.domain.Organizer;
import STE.infrastructure.database.dao.OrganizerDao;
import STE.infrastructure.database.repository.mapper.OrganizerEntityMapper;
import STE.service.managment.ObjectsCreators;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

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