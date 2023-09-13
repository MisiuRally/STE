package STE.infrastructure.database.repository;

import STE.infrastructure.database.dao.TournamentDao;
import STE.infrastructure.database.entity.TournamentEntity;
import STE.infrastructure.database.repository.jpa.TournamentJpaRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Slf4j
@Repository
@AllArgsConstructor
public class TournamentRepository implements TournamentDao {
   TournamentJpaRepository tournamentJpaRepository;

    @Override
    public List<TournamentEntity> findAllTournament() {
        return tournamentJpaRepository.findAll();
    }

    @Override
    public TournamentEntity findTournamentById(Integer id) {

        Optional<TournamentEntity> tournamentEntity = tournamentJpaRepository.findById(id);
        if (tournamentEntity.isEmpty()) {
            throw new RuntimeException("Tournament with id: [%s] does not exist".formatted(id));
        }
        return tournamentEntity.get();
    }

    @Override
    public void save(TournamentEntity tournamentEntity) {

        tournamentJpaRepository.saveAndFlush(tournamentEntity);
        log.info("TournamentSave");
    }

    @Override
    public List<TournamentEntity> findAllTournamentsByOrganizerEmail(String email) {
      return   tournamentJpaRepository.findAllTournamentsByOrganizerEmail(email);
    }

    @Override
    public void update(TournamentEntity tournamentById) {

        tournamentJpaRepository.saveAndFlush(tournamentById);

    }
}
