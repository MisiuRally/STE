package Ultima.infrastructure.database.dao;

import Ultima.domain.Tournament;
import Ultima.infrastructure.database.entity.TournamentEntity;

import java.util.List;

public interface TournamentDao {

    List<TournamentEntity> findAllTournament();
    TournamentEntity findTournamentById(Integer id);

    void save(TournamentEntity tournamentEntity);

    List<TournamentEntity> findAllTournamentsByOrganizerEmail(String email);

    void update(TournamentEntity tournamentById);
}
