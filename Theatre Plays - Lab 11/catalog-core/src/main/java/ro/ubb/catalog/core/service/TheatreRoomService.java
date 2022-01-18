package ro.ubb.catalog.core.service;

import ro.ubb.catalog.core.model.TheatreRoom;

import java.util.List;

public interface TheatreRoomService {
  TheatreRoom addTheatreRoom(TheatreRoom room);
  List<TheatreRoom> getAllTheatreRooms();
  void deleteTheatreRoom(Long id);
  TheatreRoom updateTheatreRoom(TheatreRoom room);
  List<TheatreRoom> filterRoomsByCapacity(int capacity);
  List<TheatreRoom> sortRoomsByCapacity(String direction);
  TheatreRoom getRoomWithHighestCapacity();
}
