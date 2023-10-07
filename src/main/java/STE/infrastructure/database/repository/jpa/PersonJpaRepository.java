package STE.infrastructure.database.repository.jpa;

import STE.infrastructure.database.entity.PersonEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface PersonJpaRepository extends JpaRepository<PersonEntity, Integer> {

    PersonEntity findPersonByEmail(String email);

}
