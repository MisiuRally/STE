package STE.service.managment;

import STE.API.DTO.AddressDTO;
import STE.API.DTO.OrganizerDTO;
import STE.infrastructure.database.entity.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@AllArgsConstructor
@Service
public class ObjectsCreators {



    public CompetitorEntity createCompetitorEntity() {
        return CompetitorEntity.builder()
                .competitorId(1)
                .person(createPerson())
                .startNumber(1)
                .ageCategories("OldBoy")
                .tournamentEntity(createTournamentEntity())
                .build();
    }

    public TournamentEntity createTournamentEntity() {
        return TournamentEntity.builder()
                .tournamentId(1)
                .nameOfTournament("Rajd Koguta")
                .startOfTournament("25-09-2025")
                .endOfTournament("26-09-2025")
                .startOfCompetitors("2025-08-08 12:12")
                .sportCategory("RALLY")
                .distance(100)
                .numberOfStartPlates(300)
                .organizer(createOrganizer())
                .build();
    }


    public OrganizerDTO createOrganizerDTO() {
        return OrganizerDTO.builder()
                .organizerId(1)
                .nameOfOrganizer("Unia Bardo")
                .phone("554665118")
                .email("Unia@Unia.pl")
                .address(AddressDTO.builder()
                        .country("Polska")
                        .city("Bardo")
                        .postalCode("57-256")
                        .street("Kolejowa 2")
                        .build())
                .build();
    }

    public UserEntity createUserEntity() {
        return UserEntity.builder()
                .id(1)
                .roles(Set.of(RoleEntity.builder()
                                .id(1)
                                .role("Competitor")
                        .build()))
                .userName("user")
                .password("password")
                .active(true)
                .build();
    }

    public Map<String, String> mapToTestAddNewOrganizer() {
        Map<String, String> result = new HashMap<>();

        result.put("organizerId", "1");
        result.put("nameOfOrganizer", "Max");
        result.put("phone", "554665118");
        result.put("email", "wach@wach.pl");
        result.put("country", "Polska");
        result.put("city", "DDZ");
        result.put("postalCode", "57-350");
        result.put("street", " Wacha 2");
        return result;
    }

    public Map<String, String> mapToTestAddNewTournament() {
        Map<String, String> result = new HashMap<>();

        result.put("organizerId", "1");
        result.put("nameOfOrganizer", "Unia Bardo");
        result.put("phone", "554665118");
        result.put("email", "Unia@Unia.pl");
        result.put("country", "Polska");
        result.put("city", "Bardo");
        result.put("postalCode", "57-256");
        result.put("street", "Kolejowa 2");
        return result;
    }

    public Map<String, String> dataForExistingOrganizer() {
        Map<String, String> result = new HashMap<>();

        result.put("organizerId", "1");
        result.put("nameOfOrganizer", "Gmina Bardo");
        result.put("phone", "554665118");
        result.put("email", "bardo@bardo.com");
        result.put("country", "Polska");
        result.put("city", "Bardo");
        result.put("postalCode", "57-256");
        result.put("street", "Kolejowa 2");
        result.put("active", "true");
        return result;
    }


    public PersonEntity createPerson() {
        return PersonEntity.builder()
                .personId(1122)
                .name("Jurek")
                .surname("Ogórek")
                .sex("Mężczyzna")
                .dateOfBirth("25-05-1975")
                .email("jurek@wp.pl")
                .phone("123887662")
                .address(createAddressEntity())
                .userId(createUserEntity())
                .build();


    }

    public OrganizerEntity createOrganizer() {
        return OrganizerEntity.builder()
                .organizerId(1)
                .address(createAddressEntity())
                .phone("123456789")
                .nameOfOrganizer("The Organizator")
                .email("email@gmail.com")
                .userId(UserEntity.builder()
                        .id(10)
                        .userName("Organizer")
                        .password("password")
                        .active(true)
                        .roles(Set.of(RoleEntity.builder()
                                .id(10)
                                .role("ORGANIZER")
                                .build()))
                        .build())
                .build();

    }

    public AddressEntity createAddressEntity() {
        return AddressEntity.builder()
                .addressId(1)
                .country("Polska")
                .city("Parzymiechy")
                .postalCode("45-556")
                .street("Kleszcza Mieczysława 2")
                .build();
    }


}

