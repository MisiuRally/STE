package STE.API.controller;

import STE.infrastructure.database.entity.PersonEntity;
import STE.service.PersonService;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Slf4j
@Controller
@AllArgsConstructor
public class UserController {

    private final PersonService personService;
    static final String USER = "/user";

   @GetMapping(USER)
    public String helloUser() {
        return "user";
    }


    @PostMapping("/user/add")
    public String createNewCompetitor(
            @RequestParam(value = "name") String name,
            @RequestParam(value = "surname") String surname,
            @RequestParam(value = "dateOfBirth") String dateOfBirth,
            @RequestParam(value = "email") @Email String email,
            @RequestParam(value = "phone") @Size(min = 7, max = 15) String phone,
            @RequestParam(value = "sex") String sex,
            @RequestParam(value = "country") String country,
            @RequestParam(value = "city") String city,
            @RequestParam(value = "postalCode") String postalCode,
            @RequestParam(value = "street") String street,
            @RequestParam(value = "user") String user,
            @RequestParam(value = "password") String password

    ) {
        PersonEntity personEntity = personService.personBuilder(name,
                surname,
                dateOfBirth,
                email,
                phone,
                sex,
                country,
                city,
                postalCode,
                street,
                user,
                password
        );
        log.info("Person created");
        personService.savePerson(personEntity);

        return "redirect:/user";
    }


}


