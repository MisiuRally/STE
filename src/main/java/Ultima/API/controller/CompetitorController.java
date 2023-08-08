package Ultima.API.controller;

import Ultima.API.DTO.CompetitorDTO;
import Ultima.API.DTO.PersonDTO;
import Ultima.API.DTO.mapper.CompetitorMapperDTO;
import Ultima.API.DTO.mapper.PersonMapperDTO;
import Ultima.API.DTO.mapper.TournamentMapperDTO;
import Ultima.API.DTO.mapper.TournamentMapperDTOs;
import Ultima.domain.Competitor;
import Ultima.infrastructure.database.entity.TournamentEntity;
import Ultima.infrastructure.database.repository.mapper.TournamentEntityMapper;
import Ultima.service.CompetitorService;
import Ultima.service.PersonService;
import Ultima.service.TournamentCompetitorService;
import Ultima.service.TournamentService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Slf4j
@Controller
@RequestMapping
@AllArgsConstructor
public class CompetitorController {

    public static final String COMPETITOR = "/competitor";

    private final PersonService personService;
    private final CompetitorService competitorService;
    private final TournamentService tournamentService;
    private final TournamentCompetitorService tournamentCompetitorService;
    private final CompetitorMapperDTO competitorMapperDTO;
    private final PersonMapperDTO personMapperDTO;
    private final TournamentMapperDTO tournamentMapperDTO;
    private final TournamentEntityMapper tournamentEntityMapper;
    private final TournamentMapperDTOs tournamentMapperDTOs;


    @GetMapping(COMPETITOR)
    public String competitorPage(Model model) {
        List<TournamentEntity> tournamentDTO = tournamentService.findAllTournaments();
        var listOfTournament = tournamentDTO.stream()
                .map(tournamentMapperDTOs::mapFromEntity)
                .toList();
        model.addAttribute("availableTournament", listOfTournament);
        return "/competitor";
    }


    @GetMapping("/show_all_tournament")
    public void showAllTournament() {
        tournamentService.findAllTournaments();
    }

    //    @RequestMapping
    @PostMapping("/competitor/addNew")
    public String createNewCompetitor(
            @RequestParam(value = "name") String name,
            @RequestParam(value = "surname") String surname,
            @RequestParam(value = "dateOfBirth") String dateOfBirth,
            @RequestParam(value = "email") String email,
            @RequestParam(value = "phone") String phone,
            @RequestParam(value = "sex") String sex,
            @RequestParam(value = "country") String country,
            @RequestParam(value = "city") String city,
            @RequestParam(value = "postalCode") String postalCode,
            @RequestParam(value = "street") String street
//            @RequestParam(value = "tournamentId") Integer tournamentId
    ) {
        PersonDTO person = personService.personBuilder(name,
                surname,
                dateOfBirth,
                email,
                phone,
                sex,
                country,
                city,
                postalCode,
                street);

        log.info("Person created");

        personService.savePerson(person);


        Competitor newCompetitor = competitorService.createNewCompetitor(email, 1);
        log.info("competitor created");
//
        competitorService.saveNewCompetitor(newCompetitor);
        log.info("competitor saved");

        tournamentCompetitorService.joinCompetitorWithTournament(email,1);
        log.info("competitor added");
////

//

        tournamentService.addCompetitorToTournament(1, email);
        log.info("competitor added to tournament");
//Chyba OK

        return "/competitor";
    }

//    @PostMapping(value = "/add_existing_competitor")
//    public void createExistingCompetitor(
//            @RequestParam(value = "email") String email,
//            @RequestParam(value = "tournamentId") Integer tournamentId
//    ) {
//        Person personByEmail = personService.findPersonByEmail(email);
//        TournamentDTO tournamentById = tournamentService.findTournamentById(tournamentId);
//        CompetitorDTO newCompetitor = competitorService.createNewCompetitor(personMapperDTO.mapperToDTO(personByEmail), tournamentId);
//        tournamentService.addCompetitorToTournament(tournamentMapperDTO.mapperFromDTO(tournamentById), newCompetitor);
////Chyba Ok.
//    }


}
