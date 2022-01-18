package ro.ubb.catalog.core.repository.actor.CriteriaAPI;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import ro.ubb.catalog.core.model.Actor;
import ro.ubb.catalog.core.model.Performance;
import ro.ubb.catalog.core.model.Actor_;
import ro.ubb.catalog.core.model.Performance_;
import ro.ubb.catalog.core.repository.CustomRepositorySupport;
import ro.ubb.catalog.core.repository.actor.ActorRepository;
import ro.ubb.catalog.core.repository.actor.ActorRepositoryCustom;
import ro.ubb.catalog.core.repository.play.CriteriaAPI.PlayRepositoryImpl;

import javax.persistence.EntityManager;
import javax.persistence.criteria.*;
import java.util.List;

@Component("ActorRepositoryCriteriaAPIImpl")
public class ActorRepositoryImpl extends CustomRepositorySupport
        implements ActorRepositoryCustom {
    public static final Logger log = LoggerFactory.getLogger(ActorRepository.class);

    @Override
    public List<Actor> findAllWithPerformances() {
        log.trace(" > CriteriaAPI: findAllWithPerformances - method was entered.");
        EntityManager entityManager = getEntityManager();

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Actor> query = criteriaBuilder.createQuery(Actor.class);
        query.distinct(Boolean.TRUE);

        Root<Actor> root = query.from(Actor.class);

        Fetch<Actor, Performance> actorPerformanceFetch = root.fetch(Actor_.performances, JoinType.LEFT);

        List<Actor> actors = entityManager.createQuery(query).getResultList();

        log.trace(" > CriteriaAPI: findAllWithPerformances - method was finished.");
        return actors;
    }

    @Override
    public List<Actor> findAllWithPerformancesWithPlays() {
        log.trace(" > CriteriaAPI: findAllWithPerformancesWithPlays - method was entered.");

        EntityManager entityManager = getEntityManager();

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Actor> query = criteriaBuilder.createQuery(Actor.class);
        query.distinct(Boolean.TRUE);

        Root<Actor> root = query.from(Actor.class);

        Fetch<Actor, Performance> actorPerformanceFetch = root.fetch(Actor_.performances, JoinType.LEFT);
        actorPerformanceFetch.fetch(Performance_.play, JoinType.LEFT);

        List<Actor> actors = entityManager.createQuery(query).getResultList();

        log.trace(" > CriteriaAPI: findAllWithPerformancesWithPlays - method was finished.");

        return actors;
    }
}
