package Ultima.API.controller;

import Ultima.API.DTO.OrganizerDTO;
import Ultima.API.DTO.mapper.OrganizerMapperDTO;
import Ultima.domain.Organizer;
import Ultima.service.OrganizerService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(AdministratorController.ADMINISTRATOR)
@AllArgsConstructor
public class AdministratorController {

    public static final String ADMINISTRATOR = "/administrator";
    public static final String ORGANIZE_EMAIL ="/{organizer_email}";

    private final OrganizerService organizerService;
    private final OrganizerMapperDTO organizerMapperDTO;
    @GetMapping
    public List<OrganizerDTO> showAllOrganizer(){
        List<Organizer> allOrganizer = organizerService.findAllOrganizer();
        return allOrganizer.stream()
                .map(organizerMapperDTO::mapperToDTO)
                .toList();
    }


    @GetMapping(value = ORGANIZE_EMAIL)
    public OrganizerDTO findOrganizerByEmail(@PathVariable String email){
        Organizer organizer = organizerService.findOrganizerByEmail(email);
        return organizerMapperDTO.mapperToDTO(organizer);

    }
}
