package ro.ubb.catalog.core.repository;

import org.springframework.data.domain.Sort;
import ro.ubb.catalog.core.model.Actor;

import java.util.List;

public interface ActorRepository extends CatalogRepository<Actor, Long> {
  List<Actor> findByNameContains(String name);
  List<Actor> findByOrderByAgeDesc();
  Actor findFirstByOrderByAgeDesc();
  List<Actor> findAll(Sort sortDirection);
}
