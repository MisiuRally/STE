package STE.infrastructure.database.repository.jpa;

import STE.infrastructure.database.entity.CompetitorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CompetitorJpaRepository extends JpaRepository<CompetitorEntity, Integer> {


    @Query("""
            SELECT comp FROM CompetitorEntity comp
            WHERE comp.person.email= :email
            """)
    Optional<CompetitorEntity> findCompetitorByEmail(final @Param("email") String email);

    @Query("""
            SELECT comp FROM CompetitorEntity comp
            WHERE comp.tournamentEntity.tournamentId= :tournamentId
            """)
    List<CompetitorEntity> findAllCompetitorsWithTournamentId(final @Param("tournamentId") Integer tournamentId);

    @Query("""
            SELECT comp FROM CompetitorEntity comp
            WHERE comp.tournamentEntity.tournamentId= :tournamentId
            AND comp.startNumber= :startNumber
             """)
    Optional<CompetitorEntity> findCompetitorByStartNumberAndTournamentId(final @Param("startNumber") String startNumber,
                                                                          final @Param("tournamentId") String tournamentId);

    @Query("""
             SELECT comp FROM CompetitorEntity comp
             WHERE comp.person.personId= :personId
            """)
    List<CompetitorEntity> findAllCompetitorsWithPersonId(Integer personId);


    @Query("""
             SELECT comp FROM CompetitorEntity comp
             WHERE comp.competitorId= :id
            """)
    Optional<CompetitorEntity> findCompetitorById(Integer id);
}

