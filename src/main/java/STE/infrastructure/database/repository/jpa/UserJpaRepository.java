package STE.infrastructure.database.repository.jpa;

import STE.infrastructure.database.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserJpaRepository extends JpaRepository<UserEntity,Integer> {


    @Query("""
            SELECT use FROM UserEntity use
            WHERE use.userName= :name
            """)
    UserEntity findUserByName(String name);



}
