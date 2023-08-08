package Ultima.infrastructure.database.repository.jpa;

import Ultima.infrastructure.database.entity.CompetitorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.Set;

public interface CompetitorJpaRepository extends JpaRepository<CompetitorEntity, Integer> {

    @Query("""
        SELECT comp FROM CompetitorEntity comp
        WHERE comp.person.email= :email
        """)
   Optional <CompetitorEntity> findCompetitorByEmail(final @Param("email") String email);
}
