package ro.ubb.catalog.core.service;

import ro.ubb.catalog.core.model.Actor;
import ro.ubb.catalog.core.model.Play;
import ro.ubb.catalog.core.model.Performance;

import java.util.List;

public interface PerformanceService {
    Performance addPerformance(Performance performance);
    void deletePerformance(Long playId, Long actorId);
    List<Performance> getAllPerformances();
    Performance getPerformance(Long playId, Long actorId);
    Performance updatePerformance(Performance performance);
}
