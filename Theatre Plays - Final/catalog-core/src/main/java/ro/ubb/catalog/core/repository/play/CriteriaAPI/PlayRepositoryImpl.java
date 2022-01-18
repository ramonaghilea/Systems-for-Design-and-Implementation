package ro.ubb.catalog.core.repository.play.CriteriaAPI;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import ro.ubb.catalog.core.model.Director;
import ro.ubb.catalog.core.model.Performance;
import ro.ubb.catalog.core.model.Play;
import ro.ubb.catalog.core.model.Director_;
import ro.ubb.catalog.core.model.Performance_;
import ro.ubb.catalog.core.model.Play_;
import ro.ubb.catalog.core.repository.CustomRepositorySupport;
import ro.ubb.catalog.core.repository.play.PlayRepositoryCustom;

import javax.persistence.EntityManager;
import javax.persistence.criteria.*;
import java.util.List;

@Component("PlayRepositoryCriteriaAPIImpl")
public class PlayRepositoryImpl extends CustomRepositorySupport
        implements PlayRepositoryCustom {
    public static final Logger log = LoggerFactory.getLogger(PlayRepositoryImpl.class);

    @Override
    public List<Play> findAllWithDirector() {
        log.trace(" > CriteriaAPI: findAllWithDirector - method was entered.");
        EntityManager entityManager = getEntityManager();

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Play> query = criteriaBuilder.createQuery(Play.class);
        query.distinct(Boolean.TRUE);

        Root<Play> root = query.from(Play.class);

        Fetch<Play, Director> playDirectorFetch = root.fetch(Play_.director, JoinType.LEFT);
        playDirectorFetch.fetch(Director_.office, JoinType.LEFT);

        List<Play> plays = entityManager.createQuery(query).getResultList();

        log.trace(" > CriteriaAPI: findAllWithDirector - method was finished.");
        return plays;
    }

    @Override
    public List<Play> findAllWithPerformances() {
        log.trace(" > CriteriaAPI: findAllWithPerformances - method was entered.");
        EntityManager entityManager = getEntityManager();

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Play> query = criteriaBuilder.createQuery(Play.class);
        query.distinct(Boolean.TRUE);

        Root<Play> root = query.from(Play.class);

        Fetch<Play, Director> playDirectorFetch = root.fetch(Play_.director, JoinType.LEFT);
        playDirectorFetch.fetch(Director_.office, JoinType.LEFT);
        Fetch<Play, Performance> playPerformanceFetch = root.fetch(Play_.performances, JoinType.LEFT);

        List<Play> plays = entityManager.createQuery(query).getResultList();

        log.trace(" > CriteriaAPI: findAllWithPerformances - method was finished.");
        return plays;
    }

    @Override
    public List<Play> findAllWithPerformancesWithActor() {
        log.trace(" > CriteriaAPI: findAllWithPerformancesWithActor - method was entered.");
        EntityManager entityManager = getEntityManager();

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Play> query = criteriaBuilder.createQuery(Play.class);
        query.distinct(Boolean.TRUE);

        Root<Play> root = query.from(Play.class);

        Fetch<Play, Director> playDirectorFetch = root.fetch(Play_.director, JoinType.LEFT);
        playDirectorFetch.fetch(Director_.office, JoinType.LEFT);

        Fetch<Play, Performance> playPerformanceFetch = root.fetch(Play_.performances, JoinType.LEFT);
        playPerformanceFetch.fetch(Performance_.actor, JoinType.LEFT);

        List<Play> plays = entityManager.createQuery(query).getResultList();

        log.trace(" > CriteriaAPI: findAllWithPerformancesWithActor - method was finished.");
        return plays;
    }
}
