package ro.ubb.catalog.core.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.ubb.catalog.core.model.Actor;
import ro.ubb.catalog.core.repository.actor.ActorRepository;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ActorServiceImpl implements ActorService{
  public static final Logger log = LoggerFactory.getLogger(ActorServiceImpl.class);

  @Autowired
  private ActorRepository actorRepository;

//  @Autowired
//  private ActorValidator validator;

  @Override
  public Actor addActor(Actor actor) {
    log.trace(" > addActor - method was entered. actor = {}", actor);
//    validator.validate(actor);
    Actor result = this.actorRepository.save(actor);
    log.trace(" > addActor - method was finished.");
    return result;
  }

  @Override
  public List<Actor> getAllActors() {
    log.trace(" > getAllActors - method was entered.");
    List<Actor> actors =  this.actorRepository.findAllWithPerformances();
    log.trace(" > getAllActors - result = {}", actors);
    return actors;
  }

  @Override
  public void deleteActor(Long id) {
    log.trace(" > deleteActor - method was entered. id = {}", id);
    this.actorRepository.deleteById(id);
    log.trace(" > deleteActor - method was exited.");
  }

  @Override
  @Transactional
  public Actor updateActor(Actor actor) {
    log.trace(" > updateActor - method was entered. actor = {}", actor);
//    validator.validate(actor);
    this.actorRepository.findById(actor.getId())
      .ifPresent(p -> {
        p.setName(actor.getName());
        p.setAge(actor.getAge());
        p.setGender(actor.getGender());
        //this.actorRepository.save(p);
        log.trace(" > updateActor - actor updated: actor = {}", actor);
      });
    log.trace(" > updateActor - method was exited.");
    return actor;
  }

  @Override
  public List<Actor> filterActorsByName(String name) {
    log.trace(" > filterActorsByName - method was entered.");
    List<Actor> actors = this.actorRepository.findByNameContains(name);
    log.trace(" > filterActorsByDuration - result = {}", actors);
    return actors;
  }

  @Override
  public List<Actor> sortActorsByAge() {
    log.trace(" > sortActorsByAge - method was entered.");
    List<Actor> actors = this.actorRepository.findByOrderByAgeDesc();
    log.trace(" > sortActorsByAge - result = {}", actors);
    return actors;
  }

  @Override
  public Actor getTheOldestActor(){
    log.trace(" > getTheOldestActor - method was entered.");
    Actor actor = this.actorRepository.findFirstByOrderByAgeDesc();
    log.trace(" > getTheOldestActor - result={}", actor);
    return actor;
  }


  public Sort getSortDirection(String direction){
    Sort sort = direction.equals("ASC") ? Sort.by(Sort.Direction.ASC, "age") : Sort.by(Sort.Direction.DESC, "age");
    return sort;
  }

  @Override
  public List<Actor> sortDinamically(String dir){
    Sort sortDir = getSortDirection(dir);

    return this.actorRepository.findAll(sortDir);

  }

  @Override
  public List<Actor> getAllActorsReport() {
    log.trace(" > getAllActorsReport - method was entered.");
    List<Actor> actors =  this.actorRepository.findAllWithPerformances();

    List<Actor> result = actors.stream()
            .sorted(Comparator.comparingInt(Actor::getNumberPerformances)
                    .reversed())
            .collect(Collectors.toList());

    log.trace(" > getAllActorsReport - result = {}", result);
    return result;
  }
}
