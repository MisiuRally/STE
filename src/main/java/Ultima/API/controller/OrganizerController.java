package Ultima.API.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(OrganizerController.ORGANIZER)
@AllArgsConstructor
public class OrganizerController {

    public static final String ORGANIZER = "/organizer";



}
