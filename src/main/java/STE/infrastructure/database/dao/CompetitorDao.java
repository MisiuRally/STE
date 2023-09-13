package STE.infrastructure.database.dao;

import STE.infrastructure.database.entity.CompetitorEntity;

import java.util.List;

public interface CompetitorDao {



    void saveCompetitor(CompetitorEntity competitorEntity);


    List<CompetitorEntity> findAllCopmetitors();

    CompetitorEntity findCompetitorByEmail(String email);

    List<CompetitorEntity> findAllCompetitorsWithTournamentId(Integer tournamentId);

    CompetitorEntity findCompetitorByStartNumberAndTournamentId(String  startNumber, String tournamentId);

    void update(CompetitorEntity competitorByStartNumber);

   List<CompetitorEntity> findAllCompetitorsWithPersonId(Integer personId);

    CompetitorEntity findCompetitorById(Integer id);

    void deleteCopmpetitor(CompetitorEntity competitorEntity);

}
