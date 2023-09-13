package STE.API.controller;

import STE.API.DTO.CompetitorsDTOs;
import STE.API.DTO.TournamentDTO;
import STE.API.DTO.mapper.CompetitorsMapperDTOs;
import STE.API.DTO.mapper.TournamentMapperDTO;
import STE.domain.Tournament;
import STE.infrastructure.database.entity.CompetitorEntity;
import STE.infrastructure.database.entity.TournamentEntity;
import STE.infrastructure.database.repository.mapper.TournamentEntityMapper;
import STE.service.CompetitorService;
import STE.service.TournamentService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.ParseException;
import java.util.Comparator;
import java.util.List;

@Slf4j
@Controller
@AllArgsConstructor
public class TournamentManagementController {

    private final CompetitorsMapperDTOs competitorsMapperDTOs;
    private final TournamentEntityMapper tournamentEntityMapper;
    private final TournamentMapperDTO tournamentMapperDTO;
    private final CompetitorService competitorService;
    private final TournamentService tournamentService;


    @GetMapping("/tournament_management/result")
    public String showResultOfTournament(Model model,
                                         @RequestParam(value = "tournamentId") Integer tournamentId) {
        TournamentEntity tournamentById = tournamentService.findTournamentById(tournamentId);
        Tournament tournament = tournamentEntityMapper.mapperFromEntity(tournamentById);
        TournamentDTO tournamentDTO = tournamentMapperDTO.mapperToDTO(tournament);
        List<CompetitorEntity> allCompetitorsWithTournamentId =
                competitorService.findAllCompetitorsWithTournamentId(tournamentId);
        List<CompetitorsDTOs> result = allCompetitorsWithTournamentId.stream()
                .map(competitorsMapperDTOs::mapperToDTO)
                .sorted(Comparator.comparing(CompetitorsDTOs::getStartNumber)).toList();


        model.addAttribute("result", result);
        model.addAttribute("tournamentDTO", tournamentDTO);

        return "tournament_management";
    }




    @GetMapping("/tournament_management")
    public String showAllTournament(Model model) {

        List<TournamentEntity> allTournamentsEntity = tournamentService.findAllTournaments();
        List<TournamentDTO> allTournaments = allTournamentsEntity.stream().map(tournamentEntityMapper::mapperFromEntity)
                .map(tournamentMapperDTO::mapperToDTO)
                .sorted(Comparator.comparing(TournamentDTO::getTournamentId))
                .toList();
        model.addAttribute("allTournaments", allTournaments);
        return "/tournament_management";
    }


    @PostMapping("/tournament_management/set_start_of_competitors/{id}")
    public String setStartOfCompetitors(@PathVariable("id") String tournamentId) {
        TournamentEntity tournamentById = tournamentService.findTournamentById(Integer.parseInt(tournamentId));
        tournamentService.setTimeOfStart(tournamentById);
        return "/tournament_management";
    }


    @PostMapping("/tournament_management/set_result/{st}/{id}")
    public String setResult(@PathVariable("st") String startNumber,
                            @PathVariable("id") String tournamentId) throws ParseException {

        CompetitorEntity competitorByStartNumber = competitorService.findCompetitorByStartNumber(startNumber, tournamentId);
        competitorByStartNumber.setResult(competitorService.createResult(competitorByStartNumber));
        competitorByStartNumber.setAverageSpeed(competitorService.createAverageSpeed(competitorByStartNumber));
        competitorByStartNumber.setPlace(competitorService.createPlace(tournamentId, competitorByStartNumber));
        competitorService.update(competitorByStartNumber);

        return "/tournament_management";
    }

}
