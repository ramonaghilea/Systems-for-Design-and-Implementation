package ro.ubb.catalog.core.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.ubb.catalog.core.model.TheatreRoom;
import ro.ubb.catalog.core.repository.TheatreRoomRepository;

import java.util.List;

@Service
public class TheatreRoomServiceImpl implements TheatreRoomService{
  public static final Logger log = LoggerFactory.getLogger(TheatreRoomServiceImpl.class);

  @Autowired
  private TheatreRoomRepository theatreRoomRepository;
//
//  @Autowired
//  private TheatreRoomValidator validator;

  @Override
  public TheatreRoom addTheatreRoom(TheatreRoom room) {
    log.trace(" > addTheatreRoom - method was entered. room = {}", room);
//    validator.validate(room);
    TheatreRoom result = theatreRoomRepository.save(room);
    log.trace(" > addTheatreRoom - method was finished. result = {}", result);
    return result;
  }

  @Override
  public List<TheatreRoom> getAllTheatreRooms() {
    log.trace(" > getAllTheatreRooms - method was entered.");
    List<TheatreRoom> allRooms =  theatreRoomRepository.findAll();
    log.trace(" > getAllTheatreRooms - result = {}", allRooms);
    return allRooms;
  }

  @Override
  public void deleteTheatreRoom(Long id) {
    log.trace(" > deleteTheatreRoom - method was entered. id = {}", id);
    theatreRoomRepository.deleteById(id);
    log.trace(" > deleteTheatreRoom - method was exited.");
  }

  @Override
  @Transactional
  public TheatreRoom updateTheatreRoom(TheatreRoom room) {
    log.trace(" > updateTheatreRoom - method was entered. room = {}", room);
//    validator.validate(room);
    theatreRoomRepository.findById(room.getId())
      .ifPresent(r -> {
        r.setRoomName(room.getRoomName());
        r.setCapacity(room.getCapacity());
        log.trace(" > updateTheatreRoom - room updated: room = {}", room);
      });
    log.trace(" > updateTheatreRoom - method was exited.");
    return room;
  }

  @Override
  public List<TheatreRoom> filterRoomsByCapacity(int capacity) {
    log.trace(" > filterRoomsByCapacity - method was entered.");
    List<TheatreRoom> rooms = theatreRoomRepository.findByCapacityGreaterThanEqual(capacity);
    log.trace(" > filterRoomsByCapacity - result = {}", rooms);
    return rooms;
  }

  @Override
  public List<TheatreRoom> sortRoomsByCapacity(String direction) {
    log.trace(" > sortRoomsByCapacity - method was entered.");
    List<TheatreRoom> rooms = theatreRoomRepository.findAll(getSortDirection(direction));
    log.trace(" > sortRoomsByCapacity - result = {}", rooms);
    return rooms;
  }

  public Sort getSortDirection(String direction){
    Sort sort = direction.equals("ASC") ? Sort.by(Sort.Direction.ASC, "capacity") : Sort.by(Sort.Direction.DESC, "capacity");
    return sort;
  }

  @Override
  public TheatreRoom getRoomWithHighestCapacity() {
    log.trace(" > getRoomWithHighestCapacity - method was entered.");
    TheatreRoom room = theatreRoomRepository.findDistinctFirstByOrderByCapacityDesc();
    log.trace(" > getRoomWithHighestCapacity - result = {}", room);
    return room;
  }
}
