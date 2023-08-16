package Ultima.infrastructure.database.repository.jpa;

import Ultima.infrastructure.database.entity.TournamentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TournamentJpaRepository extends JpaRepository<TournamentEntity, Integer> {


    @Query("""
            SELECT tour FROM TournamentEntity tour
            WHERE tour.organizer.email= :email
            """)
    List<TournamentEntity> findAllTournamentsByOrganizerEmail(final @Param("email") String email);
}
