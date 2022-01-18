package ro.ubb.catalog.core.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.ubb.catalog.core.model.*;
import ro.ubb.catalog.core.repository.actor.ActorRepository;
import ro.ubb.catalog.core.repository.play.PlayRepository;

import javax.xml.bind.ValidationException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PerformanceServiceImpl implements PerformanceService{
    public static final Logger log = LoggerFactory.getLogger(PerformanceServiceImpl.class);

    @Autowired
    private PlayRepository playRepository;

    @Autowired
    private ActorRepository actorRepository;

    @Override
    @Transactional
    public Performance addPerformance(Performance performance) {
        log.trace(" > addPerformance - method was entered. performance = {}", performance);

        Long playId = performance.getPlay().getId();
        Play play = this.playRepository.findAllWithPerformances()
                .stream()
                .filter(p -> p.getId().equals(playId))
                .collect(Collectors.toList())
                .get(0);

        Long actorId = performance.getActor().getId();
//        Actor actor = this.actorRepository.findAll()
//                .stream()
//                .filter(p -> p.getId().equals(actorId))
//                .collect(Collectors.toList())
//                .get(0);
        Actor actor = this.actorRepository.findOneWithPerformancesQuery(actorId);

        Optional.of(play.getPerformances()
        .stream()
        .anyMatch(perf -> perf.getActor().getId().equals(actorId)
                )
        )
                .filter(bool -> bool.equals(true))
                .ifPresent((trueVal) -> {
                    try {
                        throw new ValidationException("Performance already exists");
                    } catch (ValidationException e) {
                        e.printStackTrace();
                    }
                });

        performance.setPlay(play);
        performance.setActor(actor);

        Performance result = play.addPerformance(performance);
        actor.addPerformance(performance);

        this.playRepository.save(play);
        this.actorRepository.save(actor);

        log.trace(" > addPerformance - method was finished. performances for actor = {}", actor.getPerformances());
        log.trace(" > addPerformance - method was finished. performances for play = {}", play.getPerformances());
        return result;
    }

    @Override
    @Transactional
    public void deletePerformance(Long playId, Long actorId) {
        log.trace(" > deletePerformance - method was entered. playId = {} actorId = {}", playId, actorId);
        Play play = this.playRepository.findAllWithPerformances()
                .stream()
                .filter(p -> p.getId().equals(playId))
                .collect(Collectors.toList())
                .get(0);

        Actor actor = this.actorRepository.findAllWithPerformances()
                .stream()
                .filter(p -> p.getId().equals(actorId))
                .collect(Collectors.toList())
                .get(0);

        play.removePerformance(actorId);
        actor.removePerformance(playId);

        this.playRepository.save(play);
        this.actorRepository.save(actor);

        log.trace(" > deletePerformance - method was finished.");
    }

    @Override
    public List<Performance> getAllPerformances() {
        log.trace(" > getAllPerformances - method was entered.");

//        List<Play> plays = this.playRepository.findAllWithPerformancesWithActor();
//        List<Performance> performances = plays.stream()
//                .flatMap(play -> play.getPerformances().stream())
//                .collect(Collectors.toList());

        List<Actor> actors = this.actorRepository.findAllWithPerformancesWithPlaysQuery();
        List<Performance> performances = actors.stream()
                .flatMap(actor -> actor.getPerformances().stream())
                .collect(Collectors.toList());

        log.trace(" > getAllPerformances - result = {}", performances);
        return performances;
    }

    @Override
    public Performance getPerformance(Long playId, Long actorId) {
        log.trace(" > getPerformance - method was entered. playId = {} actorId = {}", playId, actorId);
//    Play play = this.playRepository.findById(id);
        Play play = this.playRepository.findAllWithPerformancesWithActor()
                .stream()
                .filter(p -> p.getId().equals(playId))
                .collect(Collectors.toList())
                .get(0);

        Performance performance = play.getPerformances()
                .stream()
                .filter(p -> p.getActor().getId().equals(actorId))
                .collect(Collectors.toList())
                .get(0);

        log.trace(" > getPerformance - result = {}", performance);
        return performance;
    }

    @Override
    @Transactional
    public Performance updatePerformance(Performance performance) {
        log.trace(" > updatePerformance - method was entered. performance = {}", performance);

        Long playId = performance.getPlay().getId();
        Play play = this.playRepository.findAllWithPerformances()
                .stream()
                .filter(p -> p.getId().equals(playId))
                .collect(Collectors.toList())
                .get(0);

        Long actorId = performance.getActor().getId();
        Actor actor = this.actorRepository.findAllWithPerformances()
                .stream()
                .filter(p -> p.getId().equals(actorId))
                .collect(Collectors.toList())
                .get(0);

        performance.setPlay(play);
        performance.setActor(actor);

        Performance result = play.updatePerformance(performance);
        actor.updatePerformance(performance);

        this.playRepository.save(play);
        this.actorRepository.save(actor);

        log.trace(" > updatePerformance - method was exited.");
        return performance;
    }
}
