package ro.ubb.catalog.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.ubb.catalog.core.model.Actor;
import ro.ubb.catalog.core.service.ActorService;
import ro.ubb.catalog.core.service.ActorServiceImpl;
import ro.ubb.catalog.web.converter.ActorConverter;
import ro.ubb.catalog.web.dto.ActorDTO;

import java.util.List;
import java.util.Set;

@RestController
public class ActorController {
  public static final Logger log = LoggerFactory.getLogger(ActorController.class);

  @Autowired
  private ActorService actorIService;

  @Autowired
  private ActorConverter actorConverter;

  @RequestMapping(value = "/actors")
  Set<ActorDTO> getAllActors()
  {
    log.trace("> getAllActors - method entered.");
    Set<ActorDTO> result =  actorConverter.convertModelsToDtos(actorIService.getAllActors());
    log.trace("> getAllActors - result = {}.", result);
    return result;
  }

  @RequestMapping(value = "/actors/report")
  Set<ActorDTO> getAllActorsReport()
  {
    log.trace("> getAllActorsReport - method entered.");
    Set<ActorDTO> result =  actorConverter.convertModelsToDtos(actorIService.getAllActorsReport());
    log.trace("> getAllActorsReport - result = {}.", result);
    return result;
  }

  @RequestMapping(value = "/actors", method = RequestMethod.POST)
  ActorDTO addActor(@RequestBody ActorDTO actorDTO){
    log.trace("> addActor - method entered. actorDto = {}", actorDTO);
    Actor actor = actorConverter.convertDtoToModel(actorDTO);
    log.trace("> addActor - after DTO -> Model conversion actor = {}", actor);
    Actor result = actorIService.addActor(actor);
    log.trace("> addActor - result = {}", result);
    ActorDTO resultModel = actorConverter.convertModelToDto(result);

    log.trace("> addActor - after Model -> DTO conversion actorModel = {}", resultModel);
    return resultModel;
  }

  @RequestMapping(value = "/actors/{id}", method = RequestMethod.DELETE)
  ResponseEntity<?> deleteActor(@PathVariable Long id) {
    log.trace("> deleteActor - method entered. id = {}", id);
    actorIService.deleteActor(id);
    return new ResponseEntity<>(HttpStatus.OK);
  }

  @RequestMapping(value = "/actors/{id}", method = RequestMethod.PUT)
  ActorDTO updateActor(@PathVariable Long id,
                       @RequestBody ActorDTO dto) {
    log.trace("> updateActor - method entered. actorDTO = {}", dto);
    ActorDTO result =  actorConverter.convertModelToDto(
      actorIService.updateActor(
        actorConverter.convertDtoToModel(dto)
      ));

    return result;
  }

  @RequestMapping(value = "/actors/filter/{name}", method = RequestMethod.GET)
  public Set<ActorDTO> filterActorsByName(@PathVariable String name) {
    log.trace("> filterActorsByName - method entered. duration = {}", name);
    Set<ActorDTO> result =
      actorConverter.convertModelsToDtos(
        actorIService.filterActorsByName(name));
    log.trace("> filterActorsByName - result = {}", result);
    return result;
  }

  @RequestMapping(value = "/actors/sort")
  Set<ActorDTO> sortActorsByAge()
  {
    log.trace("> sortActorsByAge - method entered.");
    List<Actor> result = actorIService.sortActorsByAge();
    Set<ActorDTO> resultDTO =
      actorConverter.convertModelsToDtos(
        result);
    log.trace("> sortActorsByAge - result = {}.", resultDTO);
    return resultDTO;
  }

  @RequestMapping(value = "/actors/oldestActor")
  ActorDTO getTheOldestActor()
  {
    log.trace("> getTheOldestActor - method entered.");
    ActorDTO result =
      actorConverter.convertModelToDto(
        actorIService.getTheOldestActor());
    log.trace("> getTheOldestActor -- result = {}.", result);
    return result;
  }


  @RequestMapping(value = "/actors/sortDyn/{dir}")
  Set<ActorDTO> sortDinamically(@PathVariable String dir){
    log.trace("> sortActorsOnDuration - method entered.");
    Set<ActorDTO> result =
      actorConverter.convertModelsToDtos(
        actorIService.sortDinamically(dir));
    log.trace("> sortActorsOnDuration - result = {}.", result);
    return result;
  }
}
