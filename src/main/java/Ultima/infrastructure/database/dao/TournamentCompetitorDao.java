package Ultima.infrastructure.database.dao;

import Ultima.domain.TournamentCompetitor;
import Ultima.infrastructure.database.entity.PersonEntity;
import Ultima.infrastructure.database.entity.TournamentCompetitorEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TournamentCompetitorDao  {

    List<TournamentCompetitorEntity> findAll();

    void save(TournamentCompetitorEntity tournamentCompetitor);

}
