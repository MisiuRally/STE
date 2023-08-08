package Ultima.infrastructure.database.repository.jpa;

import Ultima.infrastructure.database.entity.OrganizerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrganizerJpaRepository extends JpaRepository<OrganizerEntity,Integer> {

    Optional<OrganizerEntity> findByEmail(String email);
}
