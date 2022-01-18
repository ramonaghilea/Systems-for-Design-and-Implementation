package ro.ubb.catalog.core.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.ubb.catalog.core.model.Actor;
import ro.ubb.catalog.core.model.Director;
import ro.ubb.catalog.core.model.Play;
import ro.ubb.catalog.core.repository.ActorRepository;
import ro.ubb.catalog.core.repository.DirectorRepository;
import ro.ubb.catalog.core.repository.PlayRepository;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PlayServiceImpl implements PlayService{
    public static final Logger log = LoggerFactory.getLogger(PlayServiceImpl.class);

    @Autowired
    private PlayRepository playRepository;

    @Autowired
    private DirectorRepository directorRepository;

    @Autowired
    private ActorRepository actorRepository;

//
//    @Autowired
//    private PlayValidator validator;

    @Override
    public Play addPlay(Play play) {
        log.trace(" > addPlay - method was entered. play = {}", play);
//        validator.validate(play);
        Play result = this.playRepository.save(play);
        play.getDirector().addPlay(play);
        log.trace(" > addPlay - method was finished.");
        return play;
    }

    @Override
    public List<Play> getAllPlays() {
        log.trace(" > getAllPlays - method was entered.");
        List<Play> plays =  this.playRepository.findAll();
        log.trace(" > getAllPlays - result = {}", plays);
        return plays;
    }

  @Override
  public Play getPlay(Long id) {
    log.trace(" > getPlay - method was entered.");
//    Play play = this.playRepository.findById(id);
            Play play = this.playRepository.findAll()
                    .stream()
                    .filter(p -> p.getId().equals(id))
                    .collect(Collectors.toList())
              .get(0);
    log.trace(" > getPlay - result = {}", play);
    return play;
  }

  @Override
  @Transactional
    public void deletePlay(Long id) {
      log.trace(" > deletePlay - method was entered. id = {}", id);
      Play play = this.getPlay(id);

      play.getPerformances()
              .forEach( p -> {
                  p.getActor().removePerformance(play.getId());
                  this.actorRepository.save(p.getActor());
              });

      Director director = play.getDirector();
      director.removePlay(play);
      this.directorRepository.save(director);

//      play.setDirector(null);
      this.playRepository.deleteById(id);

      log.trace(" > deletePlay - method was exited.");
    }

    @Override
    @Transactional
    public Play updatePlay(Play play) {
      log.trace(" > updatePlay - method was entered. play = {}", play);

      this.playRepository.findById(play.getId())
        .ifPresent(p -> {
          p.setPlayName(play.getPlayName());
          p.setDuration(play.getDuration());
          //this.playRepository.save(p);
          log.trace(" > updatePlay - play updated: play = {}", play);
        });
      log.trace(" > updatePlay - method was exited.");
      return play;
    }

    @Override
    public List<Play> filterPlaysByDuration(Long duration) {
      log.trace(" > filterPlaysByDuration - method was entered.");
  //        List<Play> plays = this.playRepository.findAll()
  //                .stream()
  //                .filter(play -> play.getDuration().equals(duration))
  //                .collect(Collectors.toList());
      List<Play> plays = this.playRepository.findByDurationEquals(duration);
      log.trace(" > filterPlaysByDuration - result = {}", plays);
      return plays;
    }

    @Override
    public List<Play> sortPlaysOnDuration() {
      log.trace(" > sortPlaysOnDuration - method was entered.");
  //        List<Play> plays = this.playRepository.findAll()
  //                .stream()
  //                .sorted(Comparator.comparingLong(Play::getDuration)
  //                        .reversed())
  //                .collect(Collectors.toList());
      List<Play> plays = this.playRepository.findByOrderByDurationDesc();
      log.trace(" > sortPlaysOnDuration - result = {}", plays);
      return plays;
    }

    @Override
    public List<Play> sortPlaysOnDurationDir(String dir) {
      log.trace(" > sortPlaysOnDuration - method was entered.");
      List<Play> plays = this.playRepository.findAll(getSortDirection(dir));
      log.trace(" > sortPlaysOnDuration - result = {}", plays);
      return plays;
    }

    public Sort getSortDirection(String direction){
      Sort sort = direction.equals("ASC") ? Sort.by(Sort.Direction.ASC, "duration") : Sort.by(Sort.Direction.DESC, "duration");
      return sort;
    }

    @Override
    public Play getTheLongestPLay() {
      log.trace(" > getTheLongestPLay - method was entered.");
      Play play = this.playRepository.findDistinctFirstByOrderByDurationDesc();
      log.trace(" > getTheLongestPLay - result = {}", play);
      return play;
    }

    @Override
    public List<Play> getAllPlaysReport() {
        log.trace(" > getAllPlaysReport - method was entered.");
        List<Play> plays =  this.playRepository.findAll();

        List<Play> result = plays.stream()
                .sorted(Comparator.comparingInt(Play::getNumberPerformances)
                                    .reversed())
                .collect(Collectors.toList());

        log.trace(" > getAllPlaysReport - result = {}", result);
        return result;
    }
}
