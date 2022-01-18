package ro.ubb.catalog.web.converter;

import org.springframework.stereotype.Component;
import ro.ubb.catalog.core.model.Props;
import ro.ubb.catalog.web.dto.PropsDTO;

@Component
public class PropsConverter extends BaseConverter<Props, PropsDTO> {
    @Override
    public Props convertDtoToModel(PropsDTO dto) {
        return null;
    }

    @Override
    public PropsDTO convertModelToDto(Props props) {
        return null;
    }
}
