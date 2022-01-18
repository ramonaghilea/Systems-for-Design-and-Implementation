package ro.ubb.catalog.core.repository.director.SQL;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.jpa.HibernateEntityManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ro.ubb.catalog.core.model.Director;
import ro.ubb.catalog.core.repository.CustomRepositorySupport;
import ro.ubb.catalog.core.repository.director.DirectorRepository;
import ro.ubb.catalog.core.repository.director.DirectorRepositoryCustom;

import java.util.List;

@Component("DirectorRepositorySQLImpl")
public class DirectorRepositoryImpl extends CustomRepositorySupport
        implements DirectorRepositoryCustom {

    private static final Logger log = LoggerFactory.getLogger(DirectorRepository.class);

    @Override
    @Transactional
    public List<Director> findAllWithOffices() {
        log.trace(" > Native SQL: findAllWithOffices - method was entered.");

        HibernateEntityManager hibernateEntityManager = getEntityManager().unwrap(HibernateEntityManager.class);
        Session session = hibernateEntityManager.getSession();

        org.hibernate.Query query = session.createNativeQuery("select distinct {d.*},{o.*} " +
                "from director d " +
                "left join office o on d.id=o.director_id ")
                .addEntity("d", Director.class)
                .addJoin("o", "d.office")
                .addEntity("d", Director.class)
                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        List<Director> directors = query.getResultList();

        log.trace(" > Native SQL: findAllWithOffices - method was finished.");
        return directors;
    }

    @Override
    @Transactional
    public List<Director> findAllWithOfficeAndPlays() {
        log.trace(" > Native SQL: findAllWithOfficeAndPlays - method was entered.");

        HibernateEntityManager hibernateEntityManager = getEntityManager().unwrap(HibernateEntityManager.class);
        Session session = hibernateEntityManager.getSession();

        org.hibernate.Query query = session.createNativeQuery("select distinct {d.*},{o.*},{p.*} " +
                "from director d " +
                "left join office o on d.id=o.director_id " +
                "left join play p on d.id=p.director_id")
                .addEntity("d", Director.class)
                .addJoin("o", "d.office")
                .addEntity("d", Director.class)
                .addJoin("p", "d.plays")
                .addEntity("d", Director.class)
                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        List<Director> directors = query.getResultList();

        log.trace(" > Native SQL: findAllWithOfficeAndPlays - method was finished.");
        return directors;
    }
}
