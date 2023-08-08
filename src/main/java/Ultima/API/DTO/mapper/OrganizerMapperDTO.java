package Ultima.API.DTO.mapper;

import Ultima.API.DTO.AddressDTO;
import Ultima.API.DTO.OrganizerDTO;
import Ultima.domain.Address;
import Ultima.domain.Organizer;
import org.springframework.stereotype.Service;

@Service
public class OrganizerMapperDTO {


    public Organizer mapperFromDto(OrganizerDTO organizerDTO) {
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

    public OrganizerDTO mapperToDTO(Organizer organizer) {
        return OrganizerDTO.builder()
                .nameOfOrganizer(organizer.getNameOfOrganizer())
                .email(organizer.getEmail())
                .phone(organizer.getPhone())
                .address(AddressDTO.builder()
                        .country(organizer.getAddress().getCountry())
                        .city(organizer.getAddress().getCity())
                        .postalCode(organizer.getAddress().getPostalCode())
                        .street(organizer.getAddress().getStreet())
                        .build())
                .build();
    }
}
