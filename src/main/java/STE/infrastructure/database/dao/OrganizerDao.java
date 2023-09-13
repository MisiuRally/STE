package STE.infrastructure.database.dao;

import STE.domain.Organizer;
import STE.infrastructure.database.entity.OrganizerEntity;

import java.util.List;
import java.util.Optional;

public interface OrganizerDao {

    Optional<OrganizerEntity> findOrganizerByEmail(String email);

    void saveOrganizer(OrganizerEntity organizerEntity);

    List<Organizer> findAllOrganizer();
}
