package Ultima.infrastructure.database.repository.mapper;

import Ultima.domain.Address;
import Ultima.domain.Organizer;
import Ultima.infrastructure.database.entity.AddressEntity;
import Ultima.infrastructure.database.entity.OrganizerEntity;
import org.springframework.stereotype.Service;

@Service
public class OrganizerEntityMapper {


    public Organizer mapperFromEntity(OrganizerEntity entity){
        return Organizer.builder()
                .organizerId(entity.getOrganizerId())
                .nameOfOrganizer(entity.getNameOfOrganizer())
                .email(entity.getEmail())
                .phone(entity.getPhone())
                .address(Address.builder()
                        .addressId(entity.getAddress().getAddressId())
                        .country(entity.getAddress().getCountry())
                        .city(entity.getAddress().getCity())
                        .postalCode(entity.getAddress().getPostalCode())
                        .street(entity.getAddress().getPostalCode())
                        .build())
                .build();
    }

    public OrganizerEntity mapperToEntity(Organizer organizer){
        return OrganizerEntity.builder()
                .organizerId(organizer.getOrganizerId())
                .nameOfOrganizer(organizer.getNameOfOrganizer())
                .email(organizer.getEmail())
                .phone(organizer.getPhone())
                .address(AddressEntity.builder()
                        .addressId(organizer.getAddress().getAddressId())
                        .country(organizer.getAddress().getCountry())
                        .city(organizer.getAddress().getCity())
                        .postalCode(organizer.getAddress().getPostalCode())
                        .street(organizer.getAddress().getPostalCode())
                        .build())
                .build();
    }
    }

