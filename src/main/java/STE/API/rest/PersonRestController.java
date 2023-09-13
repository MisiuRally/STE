package STE.API.rest;

import STE.infrastructure.database.entity.CompetitorEntity;
import STE.infrastructure.database.entity.PersonEntity;
import STE.service.PersonService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class PersonRestController {
    private final PersonService personService;
    public static final String API_COMPETITOR = "/apiPerson";
    public static final String PERSON_UPDATE = "/update/{email}/{phone}";


    @PutMapping(API_COMPETITOR + PERSON_UPDATE)
    public ResponseEntity<CompetitorEntity> updateOfCompetitor(
            @PathVariable("email") String email,
            @PathVariable("phone") String phone
    ) {
        PersonEntity personByEmail = personService.findPersonByEmail(email);
        personByEmail.setPhone(phone);
        personService.update(personByEmail);


        return ResponseEntity.ok().build();

    }
}
