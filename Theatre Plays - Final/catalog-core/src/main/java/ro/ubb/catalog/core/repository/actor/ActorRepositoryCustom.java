package ro.ubb.catalog.core.repository.actor;

import ro.ubb.catalog.core.model.Actor;

import java.util.List;

public interface ActorRepositoryCustom {
    List<Actor> findAllWithPerformances();
    List<Actor> findAllWithPerformancesWithPlays();
}
