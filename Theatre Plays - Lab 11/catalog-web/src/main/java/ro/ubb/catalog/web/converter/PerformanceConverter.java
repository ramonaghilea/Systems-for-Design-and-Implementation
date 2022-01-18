package ro.ubb.catalog.web.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ro.ubb.catalog.core.model.Performance;
import ro.ubb.catalog.web.dto.PerformanceDTO;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class PerformanceConverter {
    @Autowired
    private PlayConverter playConverter;

    @Autowired
    private ActorConverter actorConverter;

    public Performance convertDtoToModel(PerformanceDTO dto) {
        Performance model = Performance.builder()
                .play(playConverter.convertDtoToModel(dto.getPlay()))
                .actor(actorConverter.convertDtoToModel(dto.getActor()))
                .role(dto.getRole())
                .build();
        return model;
    }

    public PerformanceDTO convertModelToDto(Performance performance) {
        PerformanceDTO dto = PerformanceDTO.builder()
                .play(playConverter.convertModelToDto(performance.getPlay()))
                .actor(actorConverter.convertModelToDto(performance.getActor()))
                .role(performance.getRole())
                .build();
        return dto;
    }

    public Set<PerformanceDTO> convertModelsToDtos(Collection<Performance> models) {
        return models.stream()
                .map(model -> convertModelToDto(model))
                .collect(Collectors.toCollection(
                        LinkedHashSet::new
                        )
                );
    }
}
