package ro.ubb.catalog.core.repository.actor;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;
import ro.ubb.catalog.core.model.Actor;
import ro.ubb.catalog.core.repository.CatalogRepository;

import java.util.List;

//@Component("ActorRepositoryJPQL")
//@Component("ActorRepositoryCriteriaAPI")
@Component("ActorRepositorySQL")
public interface ActorRepository
        extends CatalogRepository<Actor, Long>, ActorRepositoryCustom {
  List<Actor> findByNameContains(String name);
  List<Actor> findByOrderByAgeDesc();
  Actor findFirstByOrderByAgeDesc();
  List<Actor> findAll(Sort sortDirection);

  @Query("select distinct a from Actor a")
  @EntityGraph(value = "actorWithPerformances", type = EntityGraph.EntityGraphType.LOAD)
  List<Actor> findAllWithPerformancesQuery();

  @Query("select distinct a from Actor a where a.id=?1")
  @EntityGraph(value = "actorWithPerformances", type = EntityGraph.EntityGraphType.LOAD)
  Actor findOneWithPerformancesQuery(Long actorId);

  @Query("select distinct a from Actor a")
  @EntityGraph(value = "actorWithPerformancesWithPlays", type = EntityGraph.EntityGraphType.LOAD)
  List<Actor> findAllWithPerformancesWithPlaysQuery();
}
