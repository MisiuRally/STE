package STE.infrastructure.database.repository.jpa;

import STE.infrastructure.database.entity.OrganizerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface OrganizerJpaRepository extends JpaRepository<OrganizerEntity, Integer> {

    Optional<OrganizerEntity> findOrganizerByEmail(String email);
}
