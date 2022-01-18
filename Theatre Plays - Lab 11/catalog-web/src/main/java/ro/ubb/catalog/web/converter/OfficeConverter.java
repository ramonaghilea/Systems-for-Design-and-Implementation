package ro.ubb.catalog.web.converter;

import org.springframework.stereotype.Component;
import ro.ubb.catalog.core.model.Office;
import ro.ubb.catalog.web.dto.OfficeDTO;

@Component
public class OfficeConverter extends BaseConverter<Office, OfficeDTO> {
    @Override
    public Office convertDtoToModel(OfficeDTO dto) {
        Office model = Office.builder()
                .officeNumber(dto.getOfficeNumber())
                .address(dto.getAddress())
                .build();
        model.setId(dto.getId());
        return model;
    }

    @Override
    public OfficeDTO convertModelToDto(Office office) {
        OfficeDTO dto = OfficeDTO.builder()
                .officeNumber(office.getOfficeNumber())
                .address(office.getAddress())
                .build();
        dto.setId(office.getId());
        return dto;
    }
}
