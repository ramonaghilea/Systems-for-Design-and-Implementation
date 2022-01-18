package ro.ubb.catalog.web.converter;

import org.springframework.stereotype.Component;
import ro.ubb.catalog.core.model.Play;
import ro.ubb.catalog.web.dto.PlayDTO;

@Component
public class PlayConverter extends BaseConverter<Play, PlayDTO> {
    @Override
    public Play convertDtoToModel(PlayDTO dto) {
        Play model = Play.builder()
                .playName(dto.getPlayName())
                .duration(dto.getDuration())
                .build();
        model.setId(dto.getId());
        return model;
    }

    @Override
    public PlayDTO convertModelToDto(Play play) {
        PlayDTO dto = PlayDTO.builder()
                .playName(play.getPlayName())
                .duration(play.getDuration())
                .build();
        dto.setId(play.getId());
        return dto;
    }
}
