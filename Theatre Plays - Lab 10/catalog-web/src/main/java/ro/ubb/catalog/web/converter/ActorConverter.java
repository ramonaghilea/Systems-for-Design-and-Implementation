package ro.ubb.catalog.web.converter;

import org.springframework.stereotype.Component;
import ro.ubb.catalog.core.model.Actor;
import ro.ubb.catalog.core.model.Play;
import ro.ubb.catalog.web.dto.ActorDTO;
import ro.ubb.catalog.web.dto.PlayDTO;

@Component
public class ActorConverter extends BaseConverter<Actor, ActorDTO> {
  @Override
  public Actor convertDtoToModel(ActorDTO dto) {
    Actor model = Actor.builder()
      .name(dto.getName())
      .age(dto.getAge())
      .gender(dto.getGender())
      .build();
    model.setId(dto.getId());
    return model;
  }

  @Override
  public ActorDTO convertModelToDto(Actor actor) {
    ActorDTO dto = ActorDTO.builder()
      .name(actor.getName())
      .age(actor.getAge())
      .gender(actor.getGender())
      .build();
    dto.setId(actor.getId());
    return dto;
  }
}
