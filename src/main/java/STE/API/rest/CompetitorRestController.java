package STE.API.rest;

import STE.API.DTO.CompetitorsDTOs;
import STE.API.DTO.mapper.CompetitorsMapperDTOs;
import STE.infrastructure.database.entity.CompetitorEntity;
import STE.infrastructure.database.entity.PersonEntity;
import STE.service.CompetitorService;
import STE.service.PersonService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class CompetitorRestController {
    private final CompetitorService competitorService;
    private final PersonService personService;
    private final CompetitorsMapperDTOs competitorsMapperDTOs;
    public static final String API_COMPETITOR = "/apiCompetitor";
    public static final String COMPETITOR_HISTORY = "/history/{email}";

    public static final String COMPETITOR_DELETE = "/delete/{id}";


    @GetMapping(API_COMPETITOR + COMPETITOR_HISTORY)
    public ResponseEntity<List<CompetitorsDTOs>> competitorHistory(
            @PathVariable("email") String email
    ) {
        PersonEntity personEntity = personService.findPersonByEmail(email);
        List<CompetitorEntity> competitorEntity = competitorService.findAllCompetitorsWithPersonId(personEntity.getPersonId());
        return ResponseEntity.ok(competitorEntity.stream().map(competitorsMapperDTOs::mapperToDTO).toList());
    }



    @DeleteMapping(API_COMPETITOR + COMPETITOR_DELETE)
    public ResponseEntity<CompetitorEntity> deleteCompetitor(
            @PathVariable("id") Integer id
    ) {
        CompetitorEntity competitorEntity = competitorService.findCompetitorById(id);
        competitorService.deleteCopmpetitor(competitorEntity);
        return ResponseEntity.ok().build();
    }


}
