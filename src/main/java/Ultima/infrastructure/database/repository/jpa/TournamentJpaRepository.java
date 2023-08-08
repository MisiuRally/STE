package Ultima.infrastructure.database.repository.jpa;

import Ultima.infrastructure.database.entity.TournamentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TournamentJpaRepository extends JpaRepository<TournamentEntity, Integer> {


}
