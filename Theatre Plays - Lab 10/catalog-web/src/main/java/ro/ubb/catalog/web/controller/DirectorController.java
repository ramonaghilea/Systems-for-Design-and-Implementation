package ro.ubb.catalog.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ro.ubb.catalog.core.service.ActorService;
import ro.ubb.catalog.core.service.ActorServiceImpl;
import ro.ubb.catalog.core.service.DirectorService;
import ro.ubb.catalog.core.service.DirectorServiceImpl;
import ro.ubb.catalog.web.converter.ActorConverter;
import ro.ubb.catalog.web.converter.DirectorConverter;
import ro.ubb.catalog.web.dto.ActorDTO;
import ro.ubb.catalog.web.dto.DirectorDTO;

import java.util.Set;

@RestController
public class DirectorController {
  public static final Logger log = LoggerFactory.getLogger(DirectorServiceImpl.class);

  @Autowired
  private DirectorService directorService;

  @Autowired
  private DirectorConverter directorConverter;

  @RequestMapping(value = "/directors")
  Set<DirectorDTO> getAllDirectors()
  {
    log.trace("> getAllDirectors - method entered.");
    var result =  directorConverter.convertModelsToDtos(directorService.getAllDirectors());
    log.trace("> getAllDirectors - result = {}.", result);
    return result;
  }

  @RequestMapping(value = "/directors", method = RequestMethod.POST)
  DirectorDTO addDirector(@RequestBody DirectorDTO directorDTO){
    log.trace("> addDirector - method entered. directorDTO = {}", directorDTO);
    var director = directorConverter.convertDtoToModel(directorDTO);
    log.trace("> addDirector - after DTO -> Model conversion director = {}", director);
    var result = directorService.addDirector(director);
    log.trace("> addDirector - result = {}", result);
    var resultModel = directorConverter.convertModelToDto(result);

    log.trace("> addDirector - after Model -> DTO conversion directorModel = {}", resultModel);
    return resultModel;
  }
}
