package ro.ubb.catalog.web.converter;

import ro.ubb.catalog.core.model.BaseEntity;
import ro.ubb.catalog.web.dto.BaseDTO;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Created by radu.
 */

public abstract class BaseConverter<Model extends BaseEntity<Long>, Dto extends BaseDTO> implements Converter<Model, Dto> {

    public Set<Long> convertModelsToIDs(Set<Model> models) {
        return models.stream()
                .map(model -> model.getId())
                .collect(Collectors.toSet());
    }

    public Set<Long> convertDTOsToIDs(Set<Dto> dtos) {
        return dtos.stream()
                .parallel()
                .map(dto -> dto.getId())
                .collect(Collectors.toSet());
    }

    public Set<Dto> convertModelsToDtos(Collection<Model> models) {
        return models.stream()
                .map(model -> convertModelToDto(model))
                .collect(Collectors.toCollection(
                  LinkedHashSet::new
                  )
                );
    }
}
