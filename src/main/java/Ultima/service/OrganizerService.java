package Ultima.service;

import Ultima.API.DTO.OrganizerDTO;
import Ultima.domain.Address;
import Ultima.domain.Organizer;
import Ultima.infrastructure.database.dao.OrganizerDao;
import Ultima.infrastructure.database.entity.OrganizerEntity;
import Ultima.infrastructure.database.repository.OrganizerRepository;
import Ultima.infrastructure.database.repository.mapper.OrganizerEntityMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrganizerService {


    private final OrganizerEntityMapper entityMapper;
    private final OrganizerDao organizerDao;


    public void addOrganizer(OrganizerDTO organizerDTO) {
        Organizer organizer = organizerBuilder(organizerDTO);
        List<Organizer> allOrganizer = organizerDao.findAllOrganizer();
        List<String> listExistingEmail = allOrganizer.stream()
                .map(org -> org.getEmail())
                .filter(e -> e.equals(organizer.getEmail()))
                .toList();
        OrganizerEntity organizerToSave = entityMapper.mapperToEntity(organizer);
        if (listExistingEmail.isEmpty()) {
            organizerDao.saveOrganizer(organizerToSave);
        } else
            throw new RuntimeException(String.format("Organizer with email: [s] already exist", organizer.getEmail()));


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
