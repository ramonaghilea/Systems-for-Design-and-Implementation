package ro.ubb.catalog.core.service;

import ro.ubb.catalog.core.model.Actor;

import java.util.List;

public interface ActorService {
  Actor addActor(Actor actor);
  List<Actor> getAllActors();
  void deleteActor(Long id);
  Actor updateActor(Actor actor);
  List<Actor> filterActorsByName(String name);
  List<Actor> sortActorsByAge();
  Actor getTheOldestActor();
  List<Actor> sortDinamically(String dir);

  List<Actor> getAllActorsReport();
}
