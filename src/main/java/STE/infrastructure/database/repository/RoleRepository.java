package STE.infrastructure.database.repository;

import STE.infrastructure.database.entity.RoleEntity;
import STE.infrastructure.database.repository.jpa.RoleJpaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@AllArgsConstructor
public class RoleRepository {

    private final RoleJpaRepository roleJpaRepository;

    public RoleEntity findRoleByName(String name) {
        return roleJpaRepository.findRoleByName(name);
    }


}
