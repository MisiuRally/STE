package Ultima.service;

import Ultima.API.DTO.CompetitorDTO;
import Ultima.API.DTO.TournamentDTO;
import Ultima.API.DTO.mapper.CompetitorMapperDTO;
import Ultima.API.DTO.mapper.TournamentMapperDTO;
import Ultima.domain.Competitor;
import Ultima.domain.Tournament;
import Ultima.domain.TournamentCompetitor;
import Ultima.infrastructure.database.dao.CompetitorDao;
import Ultima.infrastructure.database.dao.TournamentCompetitorDao;
import Ultima.infrastructure.database.dao.TournamentDao;
import Ultima.infrastructure.database.entity.CompetitorEntity;
import Ultima.infrastructure.database.entity.TournamentCompetitorEntity;
import Ultima.infrastructure.database.entity.TournamentEntity;
import Ultima.infrastructure.database.repository.mapper.CompetitorEntityMapper;
import Ultima.infrastructure.database.repository.mapper.TournamentEntityMapper;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class TournamentService {



    private final TournamentDao tournamentDao;
    private final CompetitorDao competitorDao;
    private final TournamentCompetitorService tournamentCompetitorService;
    private final TournamentCompetitorDao tournamentCompetitorDao;
    private final CompetitorMapperDTO competitorMapperDTO;
    private  final CompetitorEntityMapper competitorEntityMapper;
    private final TournamentEntityMapper tournamentEntityMapper;
    private final TournamentMapperDTO tournamentMapperDTO;
    public Integer createStartNumber(Integer tournamentId) {
//        TournamentEntity tournamentById = tournamentDao.findTournamentById(tournamentId);
//        Integer numberOfStartPlates = tournamentById.getNumberOfStartPlates();
//        List<TournamentCompetitorEntity> all = tournamentCompetitorDao.findAll();
//        int numberOfExistCompetitor = all.stream()
//                .filter(a->a.getTournament().getTournamentId().equals(tournamentId))
//                .map(TournamentCompetitorEntity::getCompetitor)
//                .toList().size();

        return 2;
    }

    public void addCompetitorToTournament(Integer tournamentId, String email){
        TournamentEntity tournamentById = findTournamentById(tournamentId);
        CompetitorEntity competitorByEmail = competitorDao.findCompetitorByEmail(email);


        TournamentCompetitorEntity tournamentCompetitor = tournamentCompetitorService
                .createTournamentCompetitor(tournamentById, competitorByEmail);
//
        tournamentCompetitorDao.save(tournamentCompetitor);

    }

    public List<TournamentEntity> findAllTournaments() {

       return tournamentDao.findAllTournament();
    }

    public TournamentEntity findTournamentById(Integer tournamentId) {
        TournamentEntity tournamentById = tournamentDao.findTournamentById(tournamentId);

       return tournamentById;
    }
}
