package STE.service;

import STE.infrastructure.database.dao.PersonDao;
import STE.infrastructure.database.entity.AddressEntity;
import STE.infrastructure.database.entity.PersonEntity;
import STE.infrastructure.database.entity.RoleEntity;
import STE.infrastructure.database.entity.UserEntity;
import STE.infrastructure.database.repository.RoleRepository;
import STE.infrastructure.security.SecurityConfig;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@AllArgsConstructor
public class PersonService {

    private final PersonDao personDao;
    private final RoleRepository roleRepository;
        private final SecurityConfig securityConfig;


    public PersonEntity personBuilder(String name, String surname,
                                   String dateOfBirth, String email,
                                   String phone, String sex, String country,
                                   String city, String postalCode,
                                   String street,String user,String password) {


        RoleEntity roleByName = roleRepository.findRoleByName("COMPETITOR");
        Set<RoleEntity> roles= new HashSet<>();
        roles.add(roleByName);

        return PersonEntity.builder()
                .name(name)
                .surname(surname)
                .email(email)
                .phone(phone)
                .sex(sex)
                .dateOfBirth(dateOfBirth)
                .address(AddressEntity.builder()
                        .country(country)
                        .city(city)
                        .postalCode(postalCode)
                        .street(street)
                        .build())
                .userId(UserEntity.builder()
                        .userName(user)
                        .password(securityConfig.passwordEncoder().encode(password))
                        .active(true)
                        .roles(roles)
                        .build())
                .build();
    }

    public void savePerson(PersonEntity entity) {

        personDao.savePerson(entity);
    }

    public PersonEntity findPersonByEmail(String email) {

        Optional<PersonEntity> personByEmail = personDao.findPersonByEmail(email);
        if (personByEmail.isEmpty()) {
            throw new RuntimeException(("Competitor with email: [%s] does not exist." +
                    " Use option for new competitor!").formatted(email));
        }
        return personByEmail.get();
    }

    public List<PersonEntity> findAllPerson() {
       return personDao.findAllPerson();
    }

    public void update(PersonEntity personByEmail) {
        personDao.update(personByEmail);
    }
}
