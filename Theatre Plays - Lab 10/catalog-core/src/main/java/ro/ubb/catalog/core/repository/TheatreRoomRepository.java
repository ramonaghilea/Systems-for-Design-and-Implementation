package ro.ubb.catalog.core.repository;

import org.springframework.data.domain.Sort;
import ro.ubb.catalog.core.model.TheatreRoom;

import java.util.List;

public interface TheatreRoomRepository extends CatalogRepository<TheatreRoom, Long> {
  List<TheatreRoom> findByCapacityGreaterThanEqual(int capacity);
  List<TheatreRoom> findByOrderByCapacityDesc();
  TheatreRoom findDistinctFirstByOrderByCapacityDesc();
  List<TheatreRoom> findAll(Sort sortDirection);
}
