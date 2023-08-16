package Ultima.API.controller;

import Ultima.API.DTO.OrganizerDTO;
import Ultima.API.DTO.TournamentDTO;
import Ultima.API.DTO.mapper.OrganizerMapperDTO;
import Ultima.domain.Organizer;
import Ultima.infrastructure.database.dao.TournamentDao;
import Ultima.infrastructure.database.entity.TournamentEntity;
import Ultima.service.OrganizerService;
import Ultima.service.TournamentService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.awt.*;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
@AllArgsConstructor
public class AdministratorController {

    public static final String ADMINISTRATOR = "/administrator";

    private final OrganizerService organizerService;
    private final TournamentService tournamentService;
    private final OrganizerMapperDTO organizerMapperDTO;


    @GetMapping(ADMINISTRATOR)
    public String hello() {
        return ADMINISTRATOR;
    }



    @PostMapping("administrator/addNewOrganizer")
    public String addNewOrganizer(
            @RequestParam(value = "nameOfOrganizer") String nameOfOrganizer,
            @RequestParam(value = "email") String email,
            @RequestParam(value = "phone") String phone,
            @RequestParam(value = "country") String country,
            @RequestParam(value = "city") String city,
            @RequestParam(value = "postalCode") String postalCode,
            @RequestParam(value = "street") String street
    ) {

        OrganizerDTO organizerDTO = organizerService.createNewOrganizer(nameOfOrganizer, email, phone, country, city, postalCode, street);
        organizerService.saveNewOrganizer(organizerDTO);

        return "administrator";
    }


    @PostMapping("administrator/createNewTournament")
    public String createNewTournament(
            @RequestParam(value = "nameOfTournament") String nameOfTournament,
            @RequestParam(value = "numberOfStartPlates") Integer numberOfStartPlates,
            @RequestParam(value = "sportCategory") String sportCategory,
            @RequestParam(value = "startOfTournament") String startOfTournament,
            @RequestParam(value = "endOfTournament") String endOfTournament,
            @RequestParam(value = "distance") Integer distance,
            @RequestParam(value = "buyIn") BigDecimal buyIn,
            @RequestParam(value = "organizer") String organizerEmail

    ) {
        TournamentEntity tournamentEntity = tournamentService.createNewTournament(nameOfTournament,
                numberOfStartPlates, sportCategory, startOfTournament, endOfTournament,distance,buyIn, organizerEmail);
        tournamentService.saveNewTournament(tournamentEntity);
        return "administrator";
    }

    @GetMapping(value = "administrator/getData")
    public ModelAndView administratorData() {
        Map<String, ?> data = prepareDataForAdministrator();
        return new ModelAndView("organizerName", data);

    }

//    @GetMapping(value = "administrator/getDatas")
//    private String getAdministratorsDatas(Model model){
//        Map<String, ?> data = prepareDataForAdministrator();
//
//        model.addAttribute("data",data);
//
//        return "/administrator";
//    }


    private Map<String, ?> prepareDataForAdministrator() {
        Set<String> availableOrganizers = organizerService.findAllOrganizer().stream()
                .map(organizerMapperDTO::mapperToDTO)
                .map(OrganizerDTO::getNameOfOrganizer)
                .collect(Collectors.toSet());


        return Map.of(
                "availableOrganizers", availableOrganizers
        );
    }


}
