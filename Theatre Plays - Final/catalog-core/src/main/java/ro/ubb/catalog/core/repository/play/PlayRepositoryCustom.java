package ro.ubb.catalog.core.repository.play;

import ro.ubb.catalog.core.model.Play;

import java.util.List;

public interface PlayRepositoryCustom {
    List<Play> findAllWithDirector();
    List<Play> findAllWithPerformances();
    List<Play> findAllWithPerformancesWithActor();
}
