package STE.service;

import STE.API.DTO.AddressDTO;
import STE.API.DTO.OrganizerDTO;
import STE.domain.Address;
import STE.domain.Organizer;
import STE.domain.exeption.NotFoundException;
import STE.infrastructure.database.dao.OrganizerDao;
import STE.infrastructure.database.entity.OrganizerEntity;
import STE.infrastructure.database.entity.RoleEntity;
import STE.infrastructure.database.repository.RoleRepository;
import STE.infrastructure.database.repository.mapper.OrganizerEntityMapper;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class OrganizerService {


    private final OrganizerEntityMapper entityMapper;
    private final OrganizerDao organizerDao;
    private final RoleRepository roleRepository;

    public OrganizerDTO createNewOrganizer(String nameOfOrganizer, String email, String phone,
                                           String country, String city, String postalCode, String street) {
        return OrganizerDTO.builder()
                .nameOfOrganizer(nameOfOrganizer)
                .email(email)
                .phone(phone)
                .address(AddressDTO.builder()
                        .country(country)
                        .city(city)
                        .postalCode(postalCode)
                        .street(street)
                        .build())
                .build();


    }


    public void addOrganizer(OrganizerDTO organizerDTO, boolean active) {

        RoleEntity roleEntity = roleRepository.findRoleByName("ORGANIZER");
        Set<RoleEntity> role= new HashSet<>();
        role.add(roleEntity);
        Organizer organizer = organizerBuilder(organizerDTO);
        List<Organizer> allOrganizer = organizerDao.findAllOrganizer();
        List<String> listExistingEmail = allOrganizer.stream()
                .map(org -> org.getEmail())
                .filter(e -> e.equals(organizer.getEmail()))
                .toList();
        OrganizerEntity organizerToSave = entityMapper.mapperToEntity(organizer);
        organizerToSave.getUserId().setActive(active);
        organizerToSave.getUserId().setRoles(role);
        if (listExistingEmail.isEmpty()) {
            organizerDao.saveOrganizer(organizerToSave);
        } else
            throw new NotFoundException(String.format("Organizer with email: [%s] already exist", organizer.getEmail()));

    }


    public Organizer findOrganizerByEmail(String email) {
        Optional<OrganizerEntity> organizerByEmail = organizerDao.findOrganizerByEmail(email);
        if (organizerByEmail.isEmpty()) {
            throw new RuntimeException(String.format("Organizer with [%s] does not exist", email));
        }
        return entityMapper.mapperFromEntity(organizerByEmail.get());
    }

    public List<Organizer> findAllOrganizer() {
        return organizerDao.findAllOrganizer();
    }

    public Organizer organizerBuilder(OrganizerDTO organizerDTO) {
        return Organizer.builder()
                .nameOfOrganizer(organizerDTO.getNameOfOrganizer())
                .email(organizerDTO.getEmail())
                .phone(organizerDTO.getPhone())
                .address(Address.builder()
                        .country(organizerDTO.getAddress().getCountry())
                        .city(organizerDTO.getAddress().getCity())
                        .postalCode(organizerDTO.getAddress().getPostalCode())
                        .street(organizerDTO.getAddress().getStreet())
                        .build())
                .build();

    }



}
