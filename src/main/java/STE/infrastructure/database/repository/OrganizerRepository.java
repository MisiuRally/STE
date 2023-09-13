package STE.infrastructure.database.repository;

import STE.domain.Organizer;
import STE.infrastructure.database.dao.OrganizerDao;
import STE.infrastructure.database.entity.OrganizerEntity;
import STE.infrastructure.database.repository.jpa.OrganizerJpaRepository;
import STE.infrastructure.database.repository.mapper.OrganizerEntityMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class OrganizerRepository implements OrganizerDao {


    OrganizerJpaRepository organizerJpaRepository;
    OrganizerEntityMapper organizerEntityMapper;


    @Override
    public Optional<OrganizerEntity> findOrganizerByEmail(String email) {
        Optional<OrganizerEntity> organizerEntity = organizerJpaRepository.findOrganizerByEmail(email);
        if (organizerEntity.isEmpty()) {
            throw new RuntimeException("Organizer with email [%s] does not exist".formatted(email));

        } else return organizerEntity;
    }

    @Override
    public void saveOrganizer(OrganizerEntity organizerEntity) {
        organizerJpaRepository.save(organizerEntity);
    }

    public List<Organizer> findAllOrganizer() {
        return organizerJpaRepository.findAll().stream()
                .map(org -> organizerEntityMapper.mapperFromEntity(org)).toList();
    }
}
