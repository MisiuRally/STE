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
        competitorJpaRepository.save(competitorEntity);
    }

    @Override
    public List<CompetitorEntity> findAllCopmetitors() {
     return    competitorJpaRepository.findAll();
    }

    @Override
    public CompetitorEntity findCompetitorByEmail(String email) {
        Optional<CompetitorEntity> competitorByEmail = competitorJpaRepository.findCompetitorByEmail(email);
        return competitorByEmail.get();
    }
}
