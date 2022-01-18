package ro.ubb.catalog.core.repository.office;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;
import ro.ubb.catalog.core.model.Office;
import ro.ubb.catalog.core.model.Play;
import ro.ubb.catalog.core.repository.CatalogRepository;

import java.util.List;

@Component("OfficeRepositoryCriteriaAPI")
//@Component("OfficeRepositoryJPQL")
//@Component("OfficeRepositorySQL")
public interface OfficeRepository extends CatalogRepository<Office, Long>, OfficeRepositoryCustom {

    @Query("select distinct o from Office o")
    @EntityGraph(value = "officeWithDirector", type = EntityGraph.EntityGraphType.LOAD)
    List<Office> findAllWithDirectorQuery();

    @Query("select distinct o from Office o")
    @EntityGraph(value = "officeWithDirectorAndPlays", type = EntityGraph.EntityGraphType.LOAD)
    List<Office> findAllWithDirectorAndPlaysQuery();
}
