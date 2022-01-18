package ro.ubb.catalog.core.repository.play;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;
import ro.ubb.catalog.core.model.Play;
import ro.ubb.catalog.core.repository.CatalogRepository;
import ro.ubb.catalog.core.repository.play.PlayRepositoryCustom;

import java.util.List;

//@Component("PlayRepositoryCriteriaAPI")
@Component("PlayRepositoryJPQL")
//@Component("PlayRepositorySQL")
public interface PlayRepository
        extends CatalogRepository<Play, Long>, PlayRepositoryCustom {
  List<Play> findByDurationEquals(Long duration);
  List<Play> findByOrderByDurationDesc();
  Play findDistinctFirstByOrderByDurationDesc();
  Play findTopById(Long id);
  List<Play> findAll(Sort sort);


  @Query("select distinct p from Play p")
  @EntityGraph(value = "playWithDirector", type = EntityGraph.EntityGraphType.LOAD)
  List<Play> findAllWithDirectorQuery();

  @Query("select distinct p from Play p")
  @EntityGraph(value = "playWithPerformances", type = EntityGraph.EntityGraphType.LOAD)
  List<Play> findAllWithPerformancesQuery();

  @Query("select distinct p from Play p")
  @EntityGraph(value = "playWithPerformancesWithActor", type = EntityGraph.EntityGraphType.LOAD)
  List<Play> findAllWithPerformancesWithActorQuery();
}
