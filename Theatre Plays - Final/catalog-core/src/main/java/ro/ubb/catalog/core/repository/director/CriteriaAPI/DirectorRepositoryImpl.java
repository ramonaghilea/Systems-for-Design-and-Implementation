package ro.ubb.catalog.core.repository.director.CriteriaAPI;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.stereotype.Component;
import ro.ubb.catalog.core.model.Director;
import ro.ubb.catalog.core.model.Office;
import ro.ubb.catalog.core.model.Play;
import ro.ubb.catalog.core.model.Director_;
import ro.ubb.catalog.core.repository.CustomRepositorySupport;
import ro.ubb.catalog.core.repository.director.DirectorRepository;
import ro.ubb.catalog.core.repository.director.DirectorRepositoryCustom;

import javax.persistence.EntityManager;
import javax.persistence.criteria.*;
import java.util.List;

@Component("DirectorRepositoryCriteriaAPIImpl")
public class DirectorRepositoryImpl extends CustomRepositorySupport
        implements DirectorRepositoryCustom {

    private static final Logger log = LoggerFactory.getLogger(DirectorRepository.class);

    @Override
    public List<Director> findAllWithOffices() {
        log.trace(" > CriteriaAPI: findAllWithOffices - method was entered.");

        EntityManager entityManager = getEntityManager();

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Director> query = criteriaBuilder.createQuery(Director.class);
        query.distinct(Boolean.TRUE);

        Root<Director> root = query.from(Director.class);
//        query.select(root);
        Fetch<Director, Office> directorOfficeFetch = root.fetch(Director_.office, JoinType.LEFT);
//        directorOfficeFetch.fetch(Book_.publisher, JoinType.LEFT);

        List<Director> directors = entityManager.createQuery(query).getResultList();

        log.trace(" > CriteriaAPI: findAllWithOffices - method was finished.");
        return directors;
    }

    @Override
    public List<Director> findAllWithOfficeAndPlays() {
        log.trace(" > CriteriaAPI: findAllWithOfficeAndPlays - method was entered.");

        EntityManager entityManager = getEntityManager();

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Director> query = criteriaBuilder.createQuery(Director.class);
        query.distinct(Boolean.TRUE);

        Root<Director> root = query.from(Director.class);
//        query.select(root);

//        EntityType<Director> Director_ = root.getModel();
//        Metamodel metamodel = entityManager.getMetamodel();
//        EntityType<Director> Director_ = metamodel.entity(Director.class);

        Fetch<Director, Office> directorOfficeFetch = root.fetch(Director_.office, JoinType.LEFT);
        Fetch<Director, Play> directorPlaysFetch = root.fetch(Director_.plays, JoinType.LEFT);

//        directorOfficeFetch.fetch(ro.ubb.catalog.core.model.metamodel.Director_.plays, JoinType.LEFT);

        List<Director> directors = entityManager.createQuery(query).getResultList();

        log.trace(" > CriteriaAPI: findAllWithOfficeAndPlays - method was finished.");

        return directors;
    }
}
