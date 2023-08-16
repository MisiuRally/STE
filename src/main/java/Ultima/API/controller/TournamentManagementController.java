package Ultima.API.controller;

import Ultima.API.DTO.CompetitorsDTOs;
import Ultima.API.DTO.mapper.CompetitorsMapperDTOs;
import Ultima.infrastructure.database.entity.CompetitorEntity;
import Ultima.infrastructure.database.entity.TournamentEntity;
import Ultima.service.CompetitorService;
import Ultima.service.TournamentService;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;
@Slf4j
@Controller
@AllArgsConstructor
public class TournamentManagementController {

    private final CompetitorService competitorService;
    private final CompetitorsMapperDTOs competitorsMapperDTOs;
    private final TournamentService tournamentService;

    @GetMapping("/tournament_management")
    public String hallo() {
        return "tournament_management";
    }


    @PostMapping("/tournament_management/result")
    public String showResultOfTournament(Model model,
                                         @RequestParam(value = "tournamentId") Integer tournamentId) {
        List<CompetitorEntity> allCompetitorsWithTournamentId =
                competitorService.findAllCompetitorsWithTournamentId(tournamentId);
        List<CompetitorsDTOs> result = allCompetitorsWithTournamentId.stream()
                .map(competitorsMapperDTOs::mapperToDTO)
                .toList();


        model.addAttribute("result", result);
        return "/tournament_management";
    }


    @PostMapping("/tournament_management/set_start_of_competitors/{id}")
    public String setStartOfCompetitors(@PathVariable("id") String tournamentId) {
        TournamentEntity tournamentById = tournamentService.findTournamentById(Integer.parseInt(tournamentId));
        tournamentService.setTimeOfStart(tournamentById);
        return "/tournament_management";

    }


    @PostMapping("/tournament_management/set_result/{st}/{id}")
    public String setResultt(@PathVariable("st") String startNumber,
                             @PathVariable("id") String tournamentId) throws ParseException {

        log.info("#####################################REZULTAT########################################");
        CompetitorEntity competitorByStartNumber = competitorService.findCompetitorByStartNumber(startNumber,tournamentId);
        competitorByStartNumber.setResult(competitorService.createResult(competitorByStartNumber));
        competitorByStartNumber.setAverageSpeed(competitorService.createAverageSpeed(competitorByStartNumber));
        competitorService.update(competitorByStartNumber);

        return "/tournament_management";
    }

}
