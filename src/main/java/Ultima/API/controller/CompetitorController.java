package Ultima.API.controller;

import Ultima.API.DTO.PersonDTO;
import Ultima.API.DTO.mapper.TournamentMapperDTOs;
import Ultima.infrastructure.database.entity.CompetitorEntity;
import Ultima.infrastructure.database.entity.TournamentEntity;
import Ultima.service.CompetitorService;
import Ultima.service.PersonService;
import Ultima.service.TournamentService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Slf4j
@Controller
@AllArgsConstructor
public class CompetitorController {

    public static final String COMPETITOR = "/competitor";

    private final PersonService personService;
    private final CompetitorService competitorService;
    private final TournamentService tournamentService;
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
            @RequestParam(value = "street") String street,
            @RequestParam(value = "tournamentId") Integer tournamentId
    ) {
        PersonDTO personDTO = personService.personBuilder(name,
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
        personService.savePerson(personDTO);

        CompetitorEntity newCompetitor = competitorService.createNewCompetitor(email, tournamentId);
        log.info("competitor created");

        competitorService.saveNewCompetitor(newCompetitor);
        log.info("competitor saved");


        return "home";
    }



    @PostMapping(value = "/competitor/addExisting")
    public String createExistingCompetitor(
            @RequestParam(value = "email") String email,
            @RequestParam(value = "tournamentId") Integer tournamentId
    ) {
        log.info("###########################################Existing competitor");
        competitorService.addExistingCompetitorToTournament(email, tournamentId);
        return "home";
    }


}
