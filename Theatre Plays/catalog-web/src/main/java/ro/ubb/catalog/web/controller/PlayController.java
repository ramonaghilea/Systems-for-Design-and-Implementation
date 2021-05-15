package ro.ubb.catalog.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.ubb.catalog.core.service.PlayService;
import ro.ubb.catalog.core.service.PlayServiceImpl;
import ro.ubb.catalog.web.converter.PlayConverter;
import ro.ubb.catalog.web.dto.PlayDTO;

import java.util.Set;

@RestController
public class PlayController {
    public static final Logger log = LoggerFactory.getLogger(PlayController.class);

    @Autowired
    private PlayService playIService;

    @Autowired
    private PlayConverter playConverter;

    @RequestMapping(value = "/plays")
    Set<PlayDTO> getAllPlays()
    {
        log.trace("> getAllPlays - method entered.");
        var result =  playConverter.convertModelsToDtos(playIService.getAllPlays());
        log.trace("> getAllPlays - result = {}.", result);
        return result;
    }

    @RequestMapping(value = "/plays/report")
    Set<PlayDTO> getAllPlaysReport()
    {
        log.trace("> getAllPlaysReport - method entered.");
        var result =  playConverter.convertModelsToDtos(playIService.getAllPlaysReport());
        log.trace("> getAllPlaysReport - result = {}.", result);
        return result;
    }

    @RequestMapping(value = "/plays/details/{id}")
    PlayDTO getPlay(@PathVariable Long id)
    {
      log.trace("> getPlay - method entered. id = {}", id);
      var result =  playConverter.convertModelToDto(playIService.getPlay(id));
      log.trace("> getPlay - result = {}.", result);
      return result;
    }

    @RequestMapping(value = "/plays", method = RequestMethod.POST)
    PlayDTO addPlay(@RequestBody PlayDTO playDTO){
        log.trace("> addPlay - method entered. playDto = {}", playDTO);
        var play = playConverter.convertDtoToModel(playDTO);
        log.trace("> addPlay - after DTO -> Model conversion play = {}", play);
        var result = playIService.addPlay(play);
        log.trace("> addPlay - result = {}", result);
        var resultModel = playConverter.convertModelToDto(result);

        log.trace("> addPlay - after Model -> DTO conversion playModel = {}", resultModel);
        return resultModel;
    }

    @RequestMapping(value = "/plays/{id}", method = RequestMethod.DELETE)
    ResponseEntity<?> deletePlay(@PathVariable Long id) {
      log.trace("> deletePlay - method entered. id = {}", id);
      playIService.deletePlay(id);
      return new ResponseEntity<>(HttpStatus.OK);
    }

  @RequestMapping(value = "/plays/{id}", method = RequestMethod.PUT)
  PlayDTO updatePlay(@PathVariable Long id,
                     @RequestBody PlayDTO dto) {
    log.trace("> updatePlay - method entered. playDTO = {}", dto);
    PlayDTO result =  playConverter.convertModelToDto(
      playIService.updatePlay(
        playConverter.convertDtoToModel(dto)
      ));
    log.trace("> updatePlay - result of update: result = {}", result);
    return result;
  }

    @RequestMapping(value = "/plays/filter/{duration}", method = RequestMethod.GET)
    public Set<PlayDTO> filterPlaysByDuration(@PathVariable Long duration) {
      log.trace("> filterPlaysByDuration - method entered. duration = {}", duration);
      var result =
        playConverter.convertModelsToDtos(
          playIService.filterPlaysByDuration(duration));
      log.trace("> filterPlaysByDuration - result = {}", result);
      return result;
    }

    @RequestMapping(value = "/plays/sort")
    Set<PlayDTO> sortPlaysOnDuration()
    {
      log.trace("> sortPlaysOnDuration - method entered.");
      var result =
        playConverter.convertModelsToDtos(
          playIService.sortPlaysOnDuration());
      log.trace("> sortPlaysOnDuration - result = {}.", result);
      return result;
    }

    @RequestMapping(value = "/plays/sortDir/{dir}")
    Set<PlayDTO> sortPlaysOnDurationDir(@PathVariable String dir)
    {
      log.trace("> sortPlaysOnDurationDir - method entered.");
      var result =
        playConverter.convertModelsToDtos(
          playIService.sortPlaysOnDurationDir(dir));
      log.trace("> sortPlaysOnDurationDir - result = {}.", result);
      return result;
    }

    @RequestMapping(value = "/plays/getLongest")
    PlayDTO getTheLongestPlay()
    {
      log.trace("> getTheLongestPlay - method entered.");
      PlayDTO result = playConverter.convertModelToDto(this.playIService.getTheLongestPLay());

      log.trace("> getTheLongestPlay - result = {}.", result);
      return result;
    }
}
