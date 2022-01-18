package ro.ubb.catalog.core.repository.office.CriteriaAPI;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import ro.ubb.catalog.core.model.*;
import ro.ubb.catalog.core.repository.CustomRepositorySupport;
import ro.ubb.catalog.core.repository.office.OfficeRepositoryCustom;
import ro.ubb.catalog.core.repository.play.CriteriaAPI.PlayRepositoryImpl;

import javax.persistence.EntityManager;
import javax.persistence.criteria.*;
import java.util.List;

@Component("OfficeRepositoryCriteriaAPIImpl")
public class OfficeRepositoryImpl extends CustomRepositorySupport
        implements OfficeRepositoryCustom {
    public static final Logger log = LoggerFactory.getLogger(OfficeRepositoryImpl.class);

    @Override
    public List<Office> findAllWithDirector() {
        log.trace(" > CriteriaAPI: findAllWithDirector - method was entered.");
        EntityManager entityManager = getEntityManager();

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Office> query = criteriaBuilder.createQuery(Office.class);
        query.distinct(Boolean.TRUE);

        Root<Office> root = query.from(Office.class);

        Fetch<Office, Director> officeDirectorFetch = root.fetch(Office_.director, JoinType.LEFT);

        List<Office> offices = entityManager.createQuery(query).getResultList();

        log.trace(" > CriteriaAPI: findAllWithDirector - method was finished.");
        return offices;
    }

    @Override
    public List<Office> findAllWithDirectorAndPlays() {
        log.trace(" > CriteriaAPI: findAllWithDirectorAndPlays - method was entered.");
        EntityManager entityManager = getEntityManager();

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Office> query = criteriaBuilder.createQuery(Office.class);
        query.distinct(Boolean.TRUE);

        Root<Office> root = query.from(Office.class);

        Fetch<Office, Director> officeDirectorFetch = root.fetch(Office_.director, JoinType.LEFT);
        officeDirectorFetch.fetch(Director_.plays, JoinType.LEFT);

        List<Office> offices = entityManager.createQuery(query).getResultList();

        log.trace(" > CriteriaAPI: findAllWithDirectorAndPlays - method was finished.");
        return offices;
    }
}
