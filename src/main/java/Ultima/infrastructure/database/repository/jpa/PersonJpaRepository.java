package Ultima.infrastructure.database.repository.jpa;

import Ultima.infrastructure.database.dao.PersonDao;
import Ultima.infrastructure.database.entity.OrganizerEntity;
import Ultima.infrastructure.database.entity.PersonEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonJpaRepository extends JpaRepository<PersonEntity,Integer> {

    PersonEntity findPersonByEmail(String email);

}
