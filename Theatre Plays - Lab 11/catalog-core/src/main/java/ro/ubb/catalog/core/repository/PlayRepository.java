package ro.ubb.catalog.core.repository;

import org.springframework.data.domain.Sort;
import ro.ubb.catalog.core.model.Play;

import java.util.List;

public interface PlayRepository extends CatalogRepository<Play, Long> {
  List<Play> findByDurationEquals(Long duration);
  List<Play> findByOrderByDurationDesc();
  Play findDistinctFirstByOrderByDurationDesc();
  Play findTopById(Long id);

  List<Play> findAll(Sort sort);
}
