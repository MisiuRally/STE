package Ultima.infrastructure.database.repository.jpa;

import Ultima.infrastructure.database.entity.PersonEntity;
import Ultima.infrastructure.database.entity.TournamentCompetitorEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TournamentCompetitorJpaRepository extends JpaRepository<TournamentCompetitorEntity,Integer> {


}
