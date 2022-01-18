package ro.ubb.catalog.web.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ro.ubb.catalog.core.model.Actor;
import ro.ubb.catalog.core.model.Director;
import ro.ubb.catalog.core.model.Office;
import ro.ubb.catalog.core.model.Play;
import ro.ubb.catalog.web.dto.ActorDTO;
import ro.ubb.catalog.web.dto.DirectorDTO;
import ro.ubb.catalog.web.dto.OfficeDTO;
import ro.ubb.catalog.web.dto.PlayDTO;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class DirectorConverter extends BaseConverter<Director, DirectorDTO> {
  @Autowired
  private OfficeConverter officeConverter;

  @Override
  public Director convertDtoToModel(DirectorDTO dto) {
    Office officeModel = officeConverter.convertDtoToModel(dto.getOffice());
    Director model = Director.builder()
      .name(dto.getName())
      .age(dto.getAge())
      .gender(dto.getGender())
            .office(officeModel)
      .build();
    model.setId(dto.getId());
    return model;
  }

  @Override
  public DirectorDTO convertModelToDto(Director director) {
    OfficeDTO officeDTO = officeConverter.convertModelToDto(director.getOffice());
    DirectorDTO dto = DirectorDTO.builder()
      .name(director.getName())
      .age(director.getAge())
      .gender(director.getGender())
            .office(officeDTO)
      .build();
    dto.setId(director.getId());
    return dto;
  }
}
