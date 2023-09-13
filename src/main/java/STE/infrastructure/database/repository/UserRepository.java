package STE.infrastructure.database.repository;

import STE.infrastructure.database.dao.OrganizerDao;
import STE.infrastructure.database.entity.OrganizerEntity;
import STE.infrastructure.database.entity.PersonEntity;
import STE.infrastructure.database.entity.UserEntity;
import STE.infrastructure.database.repository.jpa.OrganizerJpaRepository;
import STE.infrastructure.database.repository.jpa.UserJpaRepository;
import STE.service.PersonService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@AllArgsConstructor
public class UserRepository  {
    private final PersonService personService;
    private final OrganizerDao organizerDao;
    private final OrganizerJpaRepository organizerJpaRepository;
    private final UserJpaRepository userJpaRepository;

    public UserEntity findUserByName(String name){
      return   userJpaRepository.findUserByName(name);
    }

    public UserEntity findUserByEmail(String email) {
        Optional<OrganizerEntity> organizerByEmail = organizerJpaRepository.findOrganizerByEmail(email);
        if (organizerByEmail.isPresent()){
            return organizerByEmail.get().getUserId();
        }else {
            PersonEntity personByEmail = personService.findPersonByEmail(email);
          return   personByEmail.getUserId();
        }

    }

    public void save(UserEntity userEntity) {
        userJpaRepository.saveAndFlush(userEntity);
    }
}
