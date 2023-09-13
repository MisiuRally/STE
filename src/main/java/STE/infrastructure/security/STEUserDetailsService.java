package STE.infrastructure.security;

import STE.infrastructure.database.entity.RoleEntity;
import STE.infrastructure.database.entity.UserEntity;
import STE.infrastructure.database.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class STEUserDetailsService implements UserDetailsService {


    private final UserRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String name) {

        UserEntity userEntity = userRepository.findUserByName(name);
        List<SimpleGrantedAuthority> authorityList = getUserAuthority(userEntity.getRoles());
        return buildUserForAuthentication(userEntity, authorityList);

    }

    private UserDetails buildUserForAuthentication(UserEntity user, List<SimpleGrantedAuthority> authorityList) {

        return new org.springframework.security.core.userdetails.User(
                user.getUserName(),
                user.getPassword(),
                user.getActive(),
                true,
                true,
                true,
                authorityList);
    }

    private List<SimpleGrantedAuthority> getUserAuthority(Set<RoleEntity> userRoles) {
        return userRoles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getRole()))
                .distinct()
                .collect(Collectors.toList());
    }
}
