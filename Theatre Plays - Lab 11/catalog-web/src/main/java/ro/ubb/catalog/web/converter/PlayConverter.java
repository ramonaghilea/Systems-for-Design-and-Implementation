package ro.ubb.catalog.web.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ro.ubb.catalog.core.model.Director;
import ro.ubb.catalog.core.model.Play;
import ro.ubb.catalog.web.dto.DirectorDTO;
import ro.ubb.catalog.web.dto.PlayDTO;

import java.util.HashSet;

@Component
public class PlayConverter extends BaseConverter<Play, PlayDTO> {
    @Autowired
    private DirectorConverter directorConverter;

    @Override
    public Play convertDtoToModel(PlayDTO dto) {
        Director directorModel = directorConverter.convertDtoToModel(dto.getDirector());
        Play model = Play.builder()
                .playName(dto.getPlayName())
                .duration(dto.getDuration())
                .director(directorModel)
                .performances(new HashSet<>())
                .build();
        model.setId(dto.getId());
        return model;
    }

    @Override
    public PlayDTO convertModelToDto(Play play) {
        DirectorDTO directorDTO = directorConverter.convertModelToDto(play.getDirector());
        PlayDTO dto = PlayDTO.builder()
                .playName(play.getPlayName())
                .duration(play.getDuration())
                .director(directorDTO)
                .build();
        dto.setId(play.getId());
        return dto;
    }
}
