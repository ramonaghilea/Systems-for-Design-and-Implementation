package ro.ubb.catalog.web.converter;

import org.springframework.stereotype.Component;
import ro.ubb.catalog.core.model.Actor;
import ro.ubb.catalog.core.model.Director;
import ro.ubb.catalog.web.dto.ActorDTO;
import ro.ubb.catalog.web.dto.DirectorDTO;

@Component
public class DirectorConverter extends BaseConverter<Director, DirectorDTO> {
  @Override
  public Director convertDtoToModel(DirectorDTO dto) {
    Director model = Director.builder()
      .name(dto.getName())
      .age(dto.getAge())
      .gender(dto.getGender())
      .build();
    model.setId(dto.getId());
    return model;
  }

  @Override
  public DirectorDTO convertModelToDto(Director director) {
    DirectorDTO dto = DirectorDTO.builder()
      .name(director.getName())
      .age(director.getAge())
      .gender(director.getGender())
      .build();
    dto.setId(director.getId());
    return dto;
  }
}
