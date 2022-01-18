package ro.ubb.catalog.core.repository.play.JPQL;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import ro.ubb.catalog.core.model.Play;
import ro.ubb.catalog.core.repository.CustomRepositorySupport;
import ro.ubb.catalog.core.repository.play.PlayRepositoryCustom;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

@Component("PlayRepositoryJPQLImpl")
public class PlayRepositoryImpl extends CustomRepositorySupport
        implements PlayRepositoryCustom {
    public static final Logger log = LoggerFactory.getLogger(PlayRepositoryImpl.class);

    @Override
    public List<Play> findAllWithDirector() {
        log.trace(" > JPQL: findAllWithDirector - method was entered.");
        EntityManager entityManager = getEntityManager();
        Query query = entityManager.createQuery(
                "select distinct p from Play p " +
                        "left join fetch p.director d " +
                        "left join fetch d.office");
        List<Play> plays = query.getResultList();
        log.trace(" > JPQL: findAllWithDirector - method was finished.");
        return plays;
    }

    @Override
    public List<Play> findAllWithPerformances() {
        log.trace(" > JPQL: findAllWithPerformances - method was entered.");
        EntityManager entityManager = getEntityManager();
        Query query = entityManager.createQuery(
                "select distinct p from Play p " +
                        "left join fetch p.director d " +
                        "left join fetch d.office " +
                        "left join fetch p.performances");
        List<Play> plays = query.getResultList();
        log.trace(" > JPQL: findAllWithPerformances - method was finished.");
        return plays;
    }

    @Override
    public List<Play> findAllWithPerformancesWithActor() {
        log.trace(" > JPQL: findAllWithPerformancesWithActor - method was entered.");
        EntityManager entityManager = getEntityManager();
        Query query = entityManager.createQuery(
                "select distinct p from Play p " +
                        "left join fetch p.director d " +
                        "left join fetch d.office " +
                        "left join fetch p.performances pf " +
                        "left join fetch pf.actor ");
        List<Play> plays = query.getResultList();
        log.trace(" > JPQL: findAllWithPerformancesWithActor - method was finished.");
        return plays;
    }
}
