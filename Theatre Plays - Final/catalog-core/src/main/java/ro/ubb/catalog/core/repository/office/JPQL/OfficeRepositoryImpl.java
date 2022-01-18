package ro.ubb.catalog.core.repository.office.JPQL;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import ro.ubb.catalog.core.model.Office;
import ro.ubb.catalog.core.model.Play;
import ro.ubb.catalog.core.repository.CustomRepositorySupport;
import ro.ubb.catalog.core.repository.office.OfficeRepositoryCustom;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

@Component("OfficeRepositoryJPQLImpl")
public class OfficeRepositoryImpl extends CustomRepositorySupport
        implements OfficeRepositoryCustom {
    public static final Logger log = LoggerFactory.getLogger(OfficeRepositoryImpl.class);

    @Override
    public List<Office> findAllWithDirector() {
        log.trace(" > JPQL: findAllWithDirector - method was entered.");
        EntityManager entityManager = getEntityManager();
        Query query = entityManager.createQuery(
                "select distinct o from Office o " +
                        "left join fetch o.director d ");
        List<Office> offices = query.getResultList();
        log.trace(" > JPQL: findAllWithDirector - method was finished.");
        return offices;
    }

    @Override
    public List<Office> findAllWithDirectorAndPlays() {
        log.trace(" > JPQL: findAllWithDirectorAndPlays - method was entered.");
        EntityManager entityManager = getEntityManager();
        Query query = entityManager.createQuery(
                "select distinct o from Office o " +
                        "left join fetch o.director d " +
                        "left join fetch d.plays p ");
        List<Office> offices = query.getResultList();
        log.trace(" > JPQL: findAllWithDirectorAndPlays - method was finished.");
        return offices;
    }
}
