package STE.API.controller;

import STE.API.DTO.OrganizerDTO;
import STE.API.DTO.mapper.OrganizerMapperDTO;
import STE.infrastructure.database.entity.TournamentEntity;
import STE.infrastructure.database.entity.UserEntity;
import STE.infrastructure.database.repository.UserRepository;
import STE.service.OrganizerService;
import STE.service.PersonService;
import STE.service.TournamentService;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
@Validated
@AllArgsConstructor
public class AdministratorController {

    public static final String ADMINISTRATOR = "/administrator";

    private final PersonService personService;
    private final OrganizerService organizerService;
    private final TournamentService tournamentService;
    private final OrganizerMapperDTO organizerMapperDTO;
    private final UserRepository userRepository;




    @GetMapping(ADMINISTRATOR)
    public String hello() {
        return "administrator";
    }


    @PostMapping("/administrator/addNewOrganizer")
    public String addNewOrganizer(
            @RequestParam(value = "nameOfOrganizer") String nameOfOrganizer,
            @RequestParam(value = "email") @Email String email,
            @RequestParam(value = "phone") @Size(min = 7, max = 15) String phone,
            @RequestParam(value = "country") @NotBlank String country,
            @RequestParam(value = "city") @NotBlank String city,
            @RequestParam(value = "postalCode") @NotBlank String postalCode,
            @RequestParam(value = "street") @NotBlank String street,
            boolean active

    ) {
        OrganizerDTO organizerDTO = organizerService.createNewOrganizer(nameOfOrganizer, email, phone, country,
                city, postalCode, street);
        organizerService.addOrganizer(organizerDTO,true);
        return "administrator";
    }


    @PostMapping("/administrator/createNewTournament")
    public String createNewTournament(
            @RequestParam(value = "nameOfTournament") @NotBlank String nameOfTournament,
            @RequestParam(value = "numberOfStartPlates") Integer numberOfStartPlates,
            @RequestParam(value = "sportCategory") @NotBlank String sportCategory,
            @RequestParam(value = "startOfTournament") @NotBlank String startOfTournament,
            @RequestParam(value = "endOfTournament") String endOfTournament,
            @RequestParam(value = "distance") @NotBlank Integer distance,
            @RequestParam(value = "buyIn") @NotBlank BigDecimal buyIn,
            @RequestParam(value = "organizer") @Email String organizerEmail

    ) {
        TournamentEntity tournamentEntity = tournamentService.createNewTournament(nameOfTournament,
                numberOfStartPlates, sportCategory, startOfTournament, endOfTournament, distance, buyIn, organizerEmail);
        tournamentService.saveNewTournament(tournamentEntity);
        return "administrator";
    }


    @PostMapping("/administrator/userDeactivation")
    public String userDeactivation(@RequestParam(value = "email") @Email String email) {
        UserEntity userEntity = userRepository.findUserByEmail(email);
        userEntity.setActive(false);
        userRepository.save(userEntity);
        return "administrator";
    }


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
