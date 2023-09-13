package STE.API.controller;

import STE.API.DTO.TournamentsDTOs;
import STE.API.DTO.mapper.CompetitorsMapperDTOs;
import STE.API.DTO.mapper.TournamentMapperDTOs;
import STE.infrastructure.database.entity.CompetitorEntity;
import STE.infrastructure.database.entity.PersonEntity;
import STE.infrastructure.database.entity.TournamentEntity;
import STE.service.CompetitorService;
import STE.service.PersonService;
import STE.service.TournamentService;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Comparator;
import java.util.List;

@Slf4j
@Validated
@Controller
@AllArgsConstructor
public class CompetitorController {

    public static final String COMPETITOR = "/competitor";

    private final PersonService personService;
    private final CompetitorService competitorService;
    private final TournamentService tournamentService;
    private final TournamentMapperDTOs tournamentMapperDTOs;
    private final CompetitorsMapperDTOs competitorsMapperDTOs;


    @GetMapping(COMPETITOR)
    public String competitorPage(Model model) {
        List<TournamentEntity> tournamentEntity = tournamentService.findAllTournaments();
        var listOfTournament = tournamentEntity.stream()
                .map(tournamentMapperDTOs::mapFromEntity)
                .sorted(Comparator.comparing(TournamentsDTOs::getId))
                .toList();
        model.addAttribute("availableTournament", listOfTournament);
        return "competitor";
    }


    @PostMapping(value = "/competitor/addExisting")
    public String createExistingCompetitor(
            @RequestParam(value = "email") @Email String email,
            @RequestParam(value = "tournamentId") Integer tournamentId
    ) {
        competitorService.addExistingCompetitorToTournament(email, tournamentId);
        return "redirect:/competitor";
    }

    @GetMapping("/competitor/showHistory")
    public String showCompetitorsHistory(Model model,
                                         @RequestParam(value = "email") @Email String email) {
        log.info("showHistory");
        PersonEntity personEntity = personService.findPersonByEmail(email);
        log.info(personEntity.getPersonId().toString());
        List<CompetitorEntity> competitorEntity = competitorService.findAllCompetitorsWithPersonId(personEntity.getPersonId());
        log.info(competitorEntity.toString());
        var list = competitorEntity.stream().map(competitorsMapperDTOs::mapperToDTO).toList();
            log.info(list.toString());
        model.addAttribute("historyOfCompetitor", list);
        return "competitor";
    }
}
