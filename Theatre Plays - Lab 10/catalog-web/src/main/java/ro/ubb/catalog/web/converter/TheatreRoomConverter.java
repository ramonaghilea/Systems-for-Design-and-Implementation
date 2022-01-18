package ro.ubb.catalog.web.converter;

import org.springframework.stereotype.Component;
import ro.ubb.catalog.core.model.Play;
import ro.ubb.catalog.core.model.TheatreRoom;
import ro.ubb.catalog.web.dto.PlayDTO;
import ro.ubb.catalog.web.dto.TheatreRoomDTO;

@Component
public class TheatreRoomConverter extends BaseConverter<TheatreRoom, TheatreRoomDTO> {
  @Override
  public TheatreRoom convertDtoToModel(TheatreRoomDTO dto) {
    TheatreRoom model = TheatreRoom.builder()
      .roomName(dto.getRoomName())
      .capacity(dto.getCapacity())
      .build();
    model.setId(dto.getId());
    return model;
  }

  @Override
  public TheatreRoomDTO convertModelToDto(TheatreRoom room) {
    TheatreRoomDTO dto = TheatreRoomDTO.builder()
      .roomName(room.getRoomName())
      .capacity(room.getCapacity())
      .build();
    dto.setId(room.getId());
    return dto;
  }
}
