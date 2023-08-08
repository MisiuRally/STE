package Ultima.infrastructure.database.repository;

import Ultima.domain.TournamentCompetitor;
import Ultima.infrastructure.config.HibernateConfiguration;
import Ultima.infrastructure.database.dao.TournamentCompetitorDao;
import Ultima.infrastructure.database.entity.TournamentCompetitorEntity;
import Ultima.infrastructure.database.entity.TournamentEntity;
import Ultima.infrastructure.database.repository.jpa.TournamentCompetitorJpaRepository;
import Ultima.infrastructure.database.repository.mapper.TournamentCompetitorEntityMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@Slf4j
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class TournamentCompetitorRepository implements TournamentCompetitorDao {

private final TournamentCompetitorJpaRepository tournamentCompetitorJpaRepository;

    @Override
    public List<TournamentCompetitorEntity> findAll() {

       return tournamentCompetitorJpaRepository.findAll();
    }

    @Override
    public void save(TournamentCompetitorEntity tournamentCompetitor) {
        tournamentCompetitorJpaRepository.saveAndFlush(tournamentCompetitor);

    }
    }




