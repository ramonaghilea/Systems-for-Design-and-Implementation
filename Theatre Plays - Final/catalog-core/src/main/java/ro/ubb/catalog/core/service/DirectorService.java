package ro.ubb.catalog.core.service;

import ro.ubb.catalog.core.model.Actor;
import ro.ubb.catalog.core.model.Director;
import ro.ubb.catalog.core.model.Office;

import java.util.List;

public interface DirectorService {
  Director addDirector(Director director);
  List<Director> getAllDirectors();
  Director getDirector(Long id);
  void deleteDirector(Long id);

  List<Director> getAllDirectorsReport();
}
