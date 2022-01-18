package ro.ubb.catalog.core.repository.office.SQL;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.jpa.HibernateEntityManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ro.ubb.catalog.core.model.Office;
import ro.ubb.catalog.core.model.Play;
import ro.ubb.catalog.core.repository.CustomRepositorySupport;
import ro.ubb.catalog.core.repository.office.OfficeRepositoryCustom;

import java.util.List;

@Component("OfficeRepositorySQLImpl")
public class OfficeRepositoryImpl extends CustomRepositorySupport
        implements OfficeRepositoryCustom {
    public static final Logger log = LoggerFactory.getLogger(OfficeRepositoryImpl.class);

    @Override
    @Transactional
    public List<Office> findAllWithDirector() {
        log.trace(" > Native SQL: findAllWithDirector - method was entered.");

        HibernateEntityManager hibernateEntityManager = getEntityManager().unwrap(HibernateEntityManager.class);
        Session session = hibernateEntityManager.getSession();

        org.hibernate.Query query = session.createNativeQuery(
                "select distinct {o.*},{d.*} " +
                        "from office o " +
                        "left join director d on o.director_id=d.id ")
                .addEntity("o", Office.class)
                .addJoin("d", "o.director")
                .addEntity("o", Office.class)
                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        List<Office> offices = query.getResultList();

        log.trace(" > Native SQL: findAllWithDirector - method was finished.");
        return offices;
    }

    @Override
    @Transactional
    public List<Office> findAllWithDirectorAndPlays() {
        log.trace(" > Native SQL: findAllWithDirectorAndPlays - method was entered.");

        HibernateEntityManager hibernateEntityManager = getEntityManager().unwrap(HibernateEntityManager.class);
        Session session = hibernateEntityManager.getSession();

        org.hibernate.Query query = session.createNativeQuery(
                "select distinct {o.*},{d.*},{p.*} " +
                        "from office o " +
                        "left join director d on o.director_id=d.id " +
                        "left join play p on p.director_id=d.id ")
                .addEntity("o", Office.class)
                .addJoin("d", "o.director")
                .addEntity("o", Office.class)
                .addJoin("p", "d.plays")
                .addEntity("o", Office.class)
                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        List<Office> offices = query.getResultList();

        log.trace(" > Native SQL: findAllWithDirectorAndPlays - method was finished.");
        return offices;
    }
}
