package ro.ubb.catalog.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.ubb.catalog.core.service.TheatreRoomService;
import ro.ubb.catalog.web.converter.TheatreRoomConverter;
import ro.ubb.catalog.web.dto.TheatreRoomDTO;

import java.util.Set;

@RestController
public class TheatreRoomController {
  public static final Logger log = LoggerFactory.getLogger(TheatreRoomController.class);

  @Autowired
  private TheatreRoomService theatreRoomIService;

  @Autowired
  private TheatreRoomConverter theatreRoomConverter;

  @RequestMapping(value = "/rooms")
  Set<TheatreRoomDTO> getAllTheatreRooms()
  {
    log.trace("> getAllTheatreRooms - method entered.");
    var result =  theatreRoomConverter.convertModelsToDtos(theatreRoomIService.getAllTheatreRooms());
    log.trace("> getAllTheatreRooms - result = {}.", result);
    return result;
  }

  @RequestMapping(value = "/rooms", method = RequestMethod.POST)
  TheatreRoomDTO addTheatreRoom(@RequestBody TheatreRoomDTO roomDTO){
    log.trace("> addTheatreRoom - method entered. roomDto = {}", roomDTO);
    var room = theatreRoomConverter.convertDtoToModel(roomDTO);
    log.trace("> addTheatreRoom - after DTO -> Model conversion room = {}", room);
    var result = theatreRoomIService.addTheatreRoom(room);
    log.trace("> addTheatreRoom - result = {}", result);
    var resultModel = theatreRoomConverter.convertModelToDto(result);

    log.trace("> addTheatreRoom - after Model -> DTO conversion roomModel = {}", resultModel);
    return resultModel;
  }

  @RequestMapping(value = "/rooms/{id}", method = RequestMethod.DELETE)
  ResponseEntity<?> deleteTheatreRoom(@PathVariable Long id) {
    log.trace("> deleteTheatreRoom - method entered. id = {}", id);
    theatreRoomIService.deleteTheatreRoom(id);
    return new ResponseEntity<>(HttpStatus.OK);
  }

  @RequestMapping(value = "/rooms/{id}", method = RequestMethod.PUT)
  TheatreRoomDTO updateTheatreRoom(@PathVariable Long id,
                                   @RequestBody TheatreRoomDTO dto) {
    log.trace("> updateTheatreRoom - method entered. roomDTO = {}", dto);
    TheatreRoomDTO result =  theatreRoomConverter.convertModelToDto(
      theatreRoomIService.updateTheatreRoom(
        theatreRoomConverter.convertDtoToModel(dto)
      ));
    log.trace("> updateTheatreRoom - result of update: result = {}", result);
    return result;
  }

  @RequestMapping(value = "/rooms/filter/{capacity}", method = RequestMethod.GET)
  public Set<TheatreRoomDTO> filterTheatreRoomsByCapacity(@PathVariable int capacity) {
    log.trace("> filterTheatreRoomsByCapacity - method entered. capacity = {}", capacity);
    var result =
      theatreRoomConverter.convertModelsToDtos(
        theatreRoomIService.filterRoomsByCapacity(capacity));
    log.trace("> filterTheatreRoomsByCapacity - result = {}", result);
    return result;
  }

  @RequestMapping(value = "/rooms/sort/{direction}", method = RequestMethod.GET)
  Set<TheatreRoomDTO> sortTheatreRoomsByCapacity(@PathVariable String direction)
  {
    log.trace("> sortTheatreRoomsByCapacity - method entered.");
    var result =
      theatreRoomConverter.convertModelsToDtos(
        theatreRoomIService.sortRoomsByCapacity(direction));
    log.trace("> sortTheatreRoomsByCapacity - result = {}.", result);
    return result;
  }

  @RequestMapping(value = "/rooms/getHighest")
  TheatreRoomDTO getRoomWithHighestCapacity()
  {
    log.trace("> getRoomWithHighestCapacity - method entered.");
    TheatreRoomDTO result = theatreRoomConverter.convertModelToDto(this.theatreRoomIService.getRoomWithHighestCapacity());
    log.trace("> getRoomWithHighestCapacity - result = {}.", result);
    return result;
  }

}
