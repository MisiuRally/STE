package STE.infrastructure.database.repository;

import STE.infrastructure.database.dao.CompetitorDao;
import STE.infrastructure.database.entity.CompetitorEntity;
import STE.infrastructure.database.repository.jpa.CompetitorJpaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@AllArgsConstructor
@Repository
public class CompetitorRepository implements CompetitorDao {

     CompetitorJpaRepository competitorJpaRepository;

    public void clearDataBase(){
        competitorJpaRepository.clearDB();
    }


    @Override
    public void saveCompetitor(CompetitorEntity competitorEntity) {
        competitorJpaRepository.saveAndFlush(competitorEntity);
    }

    @Override
    public List<CompetitorEntity> findAllCopmetitors() {
        return competitorJpaRepository.findAll();
    }

    @Override
    public CompetitorEntity findCompetitorByEmail(String email) {
        Optional<CompetitorEntity> competitorByEmail = competitorJpaRepository.findCompetitorByEmail(email);
        if (competitorByEmail.isEmpty()) {
            throw new RuntimeException("Competitor with email: [%s] does not exist".formatted(email));
        }
        return competitorByEmail.get();
    }

    @Override
    public List<CompetitorEntity> findAllCompetitorsWithTournamentId(Integer tournamentId) {
        return competitorJpaRepository.findAllCompetitorsWithTournamentId(tournamentId);
    }

    @Override
    public CompetitorEntity findCompetitorByStartNumberAndTournamentId(String startNumber, String tournamentId) {

        Optional<CompetitorEntity> competitorEntity = competitorJpaRepository
                .findCompetitorByStartNumberAndTournamentId(startNumber, tournamentId);
        if (competitorEntity.isPresent()) {
            return competitorEntity.get();
        } else
            throw new RuntimeException("Competitor with start number [%s] and torunament id[%s] does not exist!"
                    .formatted(startNumber, tournamentId));
    }

    @Override
    public void update(CompetitorEntity competitorByStartNumber) {
        competitorJpaRepository.saveAndFlush(competitorByStartNumber);
    }

    @Override
    public List<CompetitorEntity> findAllCompetitorsWithPersonId(Integer personId) {
        return competitorJpaRepository.findAllCompetitorsWithPersonId(personId);
    }

    @Override
    public CompetitorEntity findCompetitorById(Integer id) {
        Optional<CompetitorEntity> competitorById = competitorJpaRepository.findCompetitorById(id);
        if (competitorById.isPresent()) {
            return competitorById.get();

        } else throw new RuntimeException("Competitor with id : [%s] does not exist".formatted(id));
    }

    @Override
    public void deleteCopmpetitor(CompetitorEntity competitorEntity) {
        competitorJpaRepository.delete(competitorEntity);
    }


}


