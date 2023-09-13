package STE.infrastructure.database.repository.jpa;

import STE.infrastructure.database.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface RoleJpaRepository extends JpaRepository<RoleEntity,Integer> {


    @Query("""
            SELECT rol FROM RoleEntity rol
            WHERE rol.role= :name
            """)
    RoleEntity findRoleByName(String name);
}
