package ro.ubb.catalog.core.repository.play.SQL;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.jpa.HibernateEntityManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ro.ubb.catalog.core.model.Play;
import ro.ubb.catalog.core.repository.CustomRepositorySupport;
import ro.ubb.catalog.core.repository.play.PlayRepositoryCustom;

import java.util.List;

@Component("PlayRepositorySQLImpl")
public class PlayRepositoryImpl extends CustomRepositorySupport
        implements PlayRepositoryCustom {
    public static final Logger log = LoggerFactory.getLogger(PlayRepositoryImpl.class);

    @Override
    @Transactional
    public List<Play> findAllWithDirector() {
        log.trace(" > Native SQL: findAllWithDirector - method was entered.");
        HibernateEntityManager hibernateEntityManager = getEntityManager().unwrap(HibernateEntityManager.class);
        Session session = hibernateEntityManager.getSession();

        org.hibernate.Query query = session.createNativeQuery(
                "select distinct {p.*},{d.*},{o.*} " +
                "from play p " +
                        "left join director d on p.director_id=d.id " +
                "left join office o on d.id=o.director_id ")
                .addEntity("p", Play.class)
                .addJoin("d", "p.director")
                .addEntity("p", Play.class)
                .addJoin("o", "d.office")
                .addEntity("p", Play.class)
                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        List<Play> plays = query.getResultList();
        log.trace(" > Native SQL: findAllWithDirector - method was finished.");
        return plays;
    }

    @Override
    @Transactional
    public List<Play> findAllWithPerformances() {
        log.trace(" > Native SQL: findAllWithPerformances - method was entered.");
        HibernateEntityManager hibernateEntityManager = getEntityManager().unwrap(HibernateEntityManager.class);
        Session session = hibernateEntityManager.getSession();

        org.hibernate.Query query = session.createNativeQuery(
                "select distinct {p.*},{d.*},{o.*},{pf.*} " +
                        "from play p " +
                        "left join director d on p.director_id=d.id " +
                        "left join office o on d.id=o.director_id " +
                        "left join performance pf on p.id=pf.play_id")
                .addEntity("p", Play.class)
                .addJoin("d", "p.director")
                .addEntity("p", Play.class)
                .addJoin("o", "d.office")
                .addEntity("p", Play.class)
                .addJoin("pf", "p.performances")
                .addEntity("p", Play.class)
                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        List<Play> plays = query.getResultList();
        log.trace(" > Native SQL: findAllWithPerformances - method was finished.");
        return plays;
    }

    @Override
    @Transactional
    public List<Play> findAllWithPerformancesWithActor() {
        log.trace(" > Native SQL: findAllWithPerformancesWithActor - method was entered.");
        HibernateEntityManager hibernateEntityManager = getEntityManager().unwrap(HibernateEntityManager.class);
        Session session = hibernateEntityManager.getSession();

        org.hibernate.Query query = session.createNativeQuery(
                "select distinct {p.*},{d.*},{o.*},{pf.*},{a.*} " +
                        "from play p " +
                        "left join director d on p.director_id=d.id " +
                        "left join office o on d.id=o.director_id " +
                        "left join performance pf on p.id=pf.play_id " +
                        "left join actor a on pf.actor_id = a.id")
                .addEntity("p", Play.class)
                .addJoin("d", "p.director")
                .addEntity("p", Play.class)
                .addJoin("o", "d.office")
                .addEntity("p", Play.class)
                .addJoin("pf", "p.performances")
                .addEntity("p", Play.class)
                .addJoin("a", "pf.actor")
                .addEntity("p", Play.class)
                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        List<Play> plays = query.getResultList();
        log.trace(" > Native SQL: findAllWithPerformancesWithActor - method was finished.");
        return plays;
    }
}
