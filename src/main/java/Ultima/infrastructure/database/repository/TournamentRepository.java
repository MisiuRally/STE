package Ultima.infrastructure.database.repository;

import Ultima.infrastructure.config.HibernateConfiguration;
import Ultima.infrastructure.database.dao.TournamentDao;
import Ultima.infrastructure.database.entity.TournamentEntity;
import Ultima.infrastructure.database.repository.jpa.TournamentJpaRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Slf4j
@Repository
@AllArgsConstructor
public class TournamentRepository implements TournamentDao {
    private final TournamentJpaRepository tournamentJpaRepository;

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
//
//                try (
//                Session session = HibernateConfiguration.getSession()) {
//            if (Objects.isNull(session)) {
//                throw new RuntimeException("Session is null ");
//            }
//            session.beginTransaction();
//
//            session.merge(tournamentById);
//            session.getTransaction().commit();
//            log.info("tournament updated");
//
//        }
        tournamentJpaRepository.saveAndFlush(tournamentById);

    }
}
