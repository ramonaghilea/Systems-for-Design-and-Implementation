package ro.ubb.catalog.core.repository.actor.JPQL;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import ro.ubb.catalog.core.model.Actor;
import ro.ubb.catalog.core.repository.CustomRepositorySupport;
import ro.ubb.catalog.core.repository.actor.ActorRepository;
import ro.ubb.catalog.core.repository.actor.ActorRepositoryCustom;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

@Component("ActorRepositoryJPQLImpl")
public class ActorRepositoryImpl extends CustomRepositorySupport
        implements ActorRepositoryCustom {
    public static final Logger log = LoggerFactory.getLogger(ActorRepository.class);

    @Override
    public List<Actor> findAllWithPerformances() {
        log.trace(" > JPQL: findAllWithPerformances - method was entered.");
        EntityManager entityManager = getEntityManager();
        Query query = entityManager.createQuery(
                "select distinct a from Actor a " +
                        "left join fetch a.performances");
        List<Actor> actors = query.getResultList();

        log.trace(" > JPQL: findAllWithPerformances - method was finished.");
        return actors;
    }

    @Override
    public List<Actor> findAllWithPerformancesWithPlays() {
        log.trace(" > JPQL: findAllWithPerformancesWithPlays - method was entered.");
        EntityManager entityManager = getEntityManager();
        Query query = entityManager.createQuery(
                "select distinct a from Actor a " +
                        "left join fetch a.performances pf " +
                        "left join pf.play");
        List<Actor> actors = query.getResultList();

        log.trace(" > JPQL: findAllWithPerformancesWithPlays - method was finished.");
        return actors;
    }
}
