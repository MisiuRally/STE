package Ultima.service;

import Ultima.domain.Competitor;
import Ultima.domain.Tournament;
import Ultima.infrastructure.database.dao.CompetitorDao;
import Ultima.infrastructure.database.dao.TournamentCompetitorDao;
import Ultima.infrastructure.database.dao.TournamentDao;
import Ultima.infrastructure.database.entity.CompetitorEntity;
import Ultima.infrastructure.database.entity.TournamentCompetitorEntity;
import Ultima.infrastructure.database.entity.TournamentEntity;
import Ultima.infrastructure.database.repository.mapper.CompetitorEntityMapper;
import Ultima.infrastructure.database.repository.mapper.TournamentEntityMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@AllArgsConstructor
public class TournamentCompetitorService {


    private final CompetitorDao competitorDao;
    private final TournamentDao tournamentDao;

    public TournamentCompetitorEntity createTournamentCompetitor(TournamentEntity tournamentEntity, CompetitorEntity competitorEntity) {
                Set<CompetitorEntity> setOfCompetitors=new HashSet<>();
                setOfCompetitors.add(competitorEntity);

        return TournamentCompetitorEntity.builder()
                .tournamentEntity(tournamentEntity)
                .build().withCompetitorEntities(setOfCompetitors);

    }

    public void joinCompetitorWithTournament(String email, int id) {
        CompetitorEntity competitorByEmail = competitorDao.findCompetitorByEmail(email);
        TournamentEntity tournamentById = tournamentDao.findTournamentById(id);


        Set<CompetitorEntity> competitorEntity = tournamentById.getCompetitorEntity();
        competitorEntity.add(competitorByEmail);
        TournamentEntity tournamentEntity = tournamentById.withCompetitorEntity(competitorEntity);


        tournamentDao.save(tournamentEntity);


    }
}
