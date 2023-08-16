package Ultima.infrastructure.database.repository;

import Ultima.infrastructure.database.dao.CompetitorDao;
import Ultima.infrastructure.database.entity.CompetitorEntity;
import Ultima.infrastructure.database.repository.jpa.CompetitorJpaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class CompetitorRepository implements CompetitorDao {

    CompetitorJpaRepository competitorJpaRepository;



    @Override
    public void saveCompetitor(CompetitorEntity competitorEntity) {
        competitorJpaRepository.saveAndFlush(competitorEntity);
    }

    @Override
    public List<CompetitorEntity> findAllCopmetitors() {
     return    competitorJpaRepository.findAll();
    }

    @Override
    public CompetitorEntity findCompetitorByEmail(String email) {
        Optional<CompetitorEntity> competitorByEmail = competitorJpaRepository.findCompetitorByEmail(email);
        if(competitorByEmail.isEmpty()){
            throw new RuntimeException("Competitor with email: [%s] does not exist".formatted(email));
        }
        return competitorByEmail.get();
    }

    @Override
    public List<CompetitorEntity> findAllCompetitorsWithTournamentId(Integer tournamentId) {
      return   competitorJpaRepository.findAllCompetitorsWithTournamentId(tournamentId);
    }

    @Override
    public CompetitorEntity findCompetitorByStartNumberAndTournamentId(String  startNumber, String tournamentId) {
       return competitorJpaRepository.findCompetitorByStartNumberAndTournamentId(startNumber,tournamentId);
    }

    @Override
    public void update(CompetitorEntity competitorByStartNumber) {
        competitorJpaRepository.saveAndFlush(competitorByStartNumber);
    }
}
