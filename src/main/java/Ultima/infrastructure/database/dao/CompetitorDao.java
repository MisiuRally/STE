package Ultima.infrastructure.database.dao;

import Ultima.infrastructure.database.entity.CompetitorEntity;

import java.util.List;
import java.util.Optional;

public interface CompetitorDao {



    void saveCompetitor(CompetitorEntity competitorEntity);


    List<CompetitorEntity> findAllCopmetitors();

    CompetitorEntity findCompetitorByEmail(String email);
}
