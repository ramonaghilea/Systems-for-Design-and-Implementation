package ro.ubb.catalog.core.repository.director;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;
import ro.ubb.catalog.core.model.Director;
import ro.ubb.catalog.core.repository.CatalogRepository;

import java.util.List;

//@Component("DirectorRepositorySQL")
//@Component("DirectorRepositoryCriteriaAPI")
@Component("DirectorRepositoryJPQL")
public interface DirectorRepository
        extends CatalogRepository<Director, Long>, DirectorRepositoryCustom {

    @Query("select distinct d from Director d")
    @EntityGraph(value = "directorWithOffice", type = EntityGraph.EntityGraphType.LOAD)
    List<Director> findAllWithOfficesQuery();

    @Query("select distinct d from Director d")
    @EntityGraph(value = "directorWithOfficeAndPlays", type = EntityGraph.EntityGraphType.LOAD)
    List<Director> findAllWithOfficeAndPlaysQuery();
}
