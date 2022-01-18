package ro.ubb.catalog.core.repository.director.JPQL;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.stereotype.Component;
import ro.ubb.catalog.core.model.Director;
import ro.ubb.catalog.core.repository.CustomRepositorySupport;
import ro.ubb.catalog.core.repository.director.DirectorRepository;
import ro.ubb.catalog.core.repository.director.DirectorRepositoryCustom;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

@Component("DirectorRepositoryJPQLImpl")
public class DirectorRepositoryImpl extends CustomRepositorySupport
        implements DirectorRepositoryCustom {
    private static final Logger log = LoggerFactory.getLogger(DirectorRepository.class);

    @Override
    public List<Director> findAllWithOffices() {
        log.trace(" > JPQL: findAllWithOffices - method was entered. ");

        EntityManager entityManager = getEntityManager();
        Query query = entityManager.createQuery(
                "select distinct d from Director d " +
                        "left join fetch d.office");
        List<Director> directors = query.getResultList();

        log.trace(" > JPQL: findAllWithOffices - method was finished. ");
        return directors;
    }

    @Override
    public List<Director> findAllWithOfficeAndPlays() {
        log.trace(" > JPQL: findAllWithOfficeAndPlays - method was entered. ");

        EntityManager entityManager = getEntityManager();
        Query query = entityManager.createQuery(
                "select distinct d from Director d " +
                        "left join fetch d.office " +
                        "left join fetch d.plays ");
        List<Director> directors = query.getResultList();

        log.trace(" > JPQL: findAllWithOfficeAndPlays - method was finished. ");
        return directors;
    }
}
