package Ultima.infrastructure.database.repository;

import Ultima.domain.Organizer;
import Ultima.infrastructure.config.HibernateConfiguration;
import Ultima.infrastructure.database.dao.OrganizerDao;
import Ultima.infrastructure.database.entity.OrganizerEntity;
import Ultima.infrastructure.database.repository.jpa.OrganizerJpaRepository;
import Ultima.infrastructure.database.repository.mapper.OrganizerEntityMapper;
import lombok.AllArgsConstructor;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class OrganizerRepository implements OrganizerDao {


    private final OrganizerJpaRepository organizerJpaRepository;
    private final OrganizerEntityMapper organizerEntityMapper;

    @Override
    public Optional<OrganizerEntity> findOrganizerByEmail(String email) {
        Optional<OrganizerEntity> organizerEntity = organizerJpaRepository.findByEmail(email);
        if(organizerEntity.isEmpty()){
            throw new RuntimeException("Organizer with email [%s] does not exist".formatted(email));

        }else return organizerEntity;
    }

    @Override
    public void saveOrganizer(OrganizerEntity organizerEntity) {

//        try (
//                Session session = HibernateConfiguration.getSession()) {
//            if (Objects.isNull(session)) {
//                throw new RuntimeException("Session is null ");
//            }
//            session.beginTransaction();
//            session.persist(organizerEntity);
//            session.getTransaction().commit();
//
//        }
        organizerJpaRepository.save(organizerEntity);
    }

    public List<Organizer> findAllOrganizer() {
        return organizerJpaRepository.findAll().stream()
                .map(org->organizerEntityMapper.mapperFromEntity(org)).toList();
    }
}
