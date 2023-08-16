package Ultima.API.controller;

import Ultima.API.DTO.CompetitorsDTOs;
import Ultima.API.DTO.mapper.CompetitorsMapperDTOs;
import Ultima.API.DTO.mapper.TournamentMapperDTOs;
import Ultima.infrastructure.database.entity.CompetitorEntity;
import Ultima.infrastructure.database.entity.TournamentEntity;
import Ultima.service.CompetitorService;
import Ultima.service.TournamentService;
import jakarta.websocket.server.PathParam;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@AllArgsConstructor
public class OrganizerController {
    private final TournamentService tournamentService;
    private final TournamentMapperDTOs tournamentMapperDTOs;
    private final CompetitorService competitorService;
    private final CompetitorsMapperDTOs competitorsMapperDTOs;
    static final String ORGANIZER = "/organizer";


    @GetMapping(ORGANIZER)
    public String helloOrganizer() {
        return "organizer";
    }


    @PostMapping(ORGANIZER + "/orgTour")
    public String showOrgTournaments(Model model,
                                     @RequestParam(value = "email") String email) {
        List<TournamentEntity> tournamentEntities = tournamentService.findAllTournamentsByOrganizerEmail(email);
        var listOfTournament = tournamentEntities.stream()
                .map(tournamentMapperDTOs::mapFromEntity)
                .toList();
        model.addAttribute("availableOrganizerTournaments", listOfTournament);
        return "/organizer";
    }

    @PostMapping(ORGANIZER + "/startList")
    public String showStartList(Model model,
                                     @RequestParam(value = "tournamentId") Integer tournamentId) {
        List<CompetitorEntity> allCompetitorsWithTournamentId =
                competitorService.findAllCompetitorsWithTournamentId(tournamentId);
        List<CompetitorsDTOs> startList = allCompetitorsWithTournamentId.stream()
                .map(competitorsMapperDTOs::mapperToDTO)
                .toList();


        model.addAttribute("startList", startList);
        return "/organizer";
    }

    @PostMapping(ORGANIZER + "/result")
    public String showResultOfTournament(Model model,
                                @RequestParam(value = "tournamentId") Integer tournamentId) {
        List<CompetitorEntity> allCompetitorsWithTournamentId =
                competitorService.findAllCompetitorsWithTournamentId(tournamentId);
        List<CompetitorsDTOs> result = allCompetitorsWithTournamentId.stream()
                .map(competitorsMapperDTOs::mapperToDTO)
                .toList();


        model.addAttribute("result", result);
        return "/organizer";
    }



    @PostMapping("/organizer/updateStartNumbers")
    public String updateStartNumbers(
            @RequestParam(value = "tournamentId") Integer tournamentId,
            @RequestParam(value = "numberOfStartPlates") Integer numberOfStartPlates
    ) {
        tournamentService.updateStartNumbers(tournamentId, numberOfStartPlates);

        return "organizer";
    }

    @PostMapping("/organizer/updateDateOfTournament")
    public String updateDateOfTournament(
            @RequestParam(value = "tournamentId") Integer tournamentId,
            @RequestParam(value = "newStartDate") String newStartDate,
            @RequestParam(value = "newEndDate") String newEndDate
    ){
        tournamentService.updateDateOfTournament(tournamentId,newStartDate,newEndDate);

        return "organizer";
    }



}
