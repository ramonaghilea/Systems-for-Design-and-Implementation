package ro.ubb.catalog.core.repository.actor.SQL;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.jpa.HibernateEntityManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ro.ubb.catalog.core.model.Actor;
import ro.ubb.catalog.core.repository.CustomRepositorySupport;
import ro.ubb.catalog.core.repository.actor.ActorRepository;
import ro.ubb.catalog.core.repository.actor.ActorRepositoryCustom;

import java.util.List;

@Component("ActorRepositorySQLImpl")
public class ActorRepositoryImpl extends CustomRepositorySupport
        implements ActorRepositoryCustom {
    public static final Logger log = LoggerFactory.getLogger(ActorRepository.class);

    @Override
    @Transactional
    public List<Actor> findAllWithPerformances() {
        log.trace(" > Native SQL: findAllWithPerformances - method was entered.");

        HibernateEntityManager hibernateEntityManager = getEntityManager().unwrap(HibernateEntityManager.class);
        Session session = hibernateEntityManager.getSession();

        org.hibernate.Query query = session.createNativeQuery(
                "select distinct {a.*},{pf.*} " +
                        "from actor a " +
                        "left join performance pf on a.id=pf.actor_id")
                .addEntity("a", Actor.class)
                .addJoin("pf", "a.performances")
                .addEntity("a", Actor.class)
                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        List<Actor> actors = query.getResultList();

        log.trace(" > Native SQL: findAllWithPerformances - method was finished.");
        return actors;
    }

    @Override
    @Transactional
    public List<Actor> findAllWithPerformancesWithPlays() {
        log.trace(" > Native SQL: findAllWithPerformancesWithPlays - method was entered.");

        HibernateEntityManager hibernateEntityManager = getEntityManager().unwrap(HibernateEntityManager.class);
        Session session = hibernateEntityManager.getSession();

        org.hibernate.Query query = session.createNativeQuery(
                "select distinct {a.*},{pf.*},{p.*} " +
                        "from actor a " +
                        "left join performance pf on a.id=pf.actor_id " +
                        "left join play p on pf.play_id=p.id")
                .addEntity("a", Actor.class)
                .addJoin("pf", "a.performances")
                .addEntity("a", Actor.class)
                .addJoin("p", "pf.play")
                .addEntity("a", Actor.class)
                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        List<Actor> actors = query.getResultList();

        log.trace(" > Native SQL: findAllWithPerformancesWithPlays - method was finished.");
        return actors;
    }
}
