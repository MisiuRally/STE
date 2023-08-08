package Ultima.infrastructure.database.repository;

import Ultima.infrastructure.config.HibernateConfiguration;
import Ultima.infrastructure.database.dao.SportMeasureTimeEnterpriseManagementDAO;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Slf4j
@Service
@AllArgsConstructor

public class SportMeasureTimeEnterpriseManagementRepository implements SportMeasureTimeEnterpriseManagementDAO {

    @Override
    public void clearDataBase() {

        try (
                Session session = HibernateConfiguration.getSession()) {
            if (Objects.isNull(session)) {
                throw new RuntimeException("Session is null ");
            }
            session.beginTransaction();
            session.createMutationQuery("DELETE FROM PersonEntity ent").executeUpdate();
            session.createMutationQuery("DELETE FROM OrganizerEntity ent").executeUpdate();
            session.createMutationQuery("DELETE FROM AddressEntity ent").executeUpdate();
            session.createMutationQuery("DELETE FROM CompetitorEntity ent").executeUpdate();
            session.createMutationQuery("DELETE FROM TimeResultEntity ent").executeUpdate();
            session.createMutationQuery("DELETE FROM TimeResultTournamentEntity ent").executeUpdate();
            session.getTransaction().commit();

        }
    }
}
