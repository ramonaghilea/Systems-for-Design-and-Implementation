package ro.ubb.catalog.core.service;

import ro.ubb.catalog.core.model.Actor;
import ro.ubb.catalog.core.model.Director;

import java.util.List;

public interface DirectorService {
  Director addDirector(Director director);
  List<Director> getAllDirectors();
}
