package ro.ubb.catalog.web.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ro.ubb.catalog.core.model.Director;
import ro.ubb.catalog.core.model.Office;
import ro.ubb.catalog.web.dto.DirectorDTO;
import ro.ubb.catalog.web.dto.OfficeDTO;

@Component
public class OfficeConverter extends BaseConverter<Office, OfficeDTO> {
    @Autowired
    private DirectorConverter directorConverter;

    @Override
    public Office convertDtoToModel(OfficeDTO dto) {
        Director directorModel = directorConverter.convertDtoToModelOnlyDirector(dto.getDirector());
        Office model = Office.builder()
                .officeNumber(dto.getOfficeNumber())
                .address(dto.getAddress())
                .director(directorModel)
                .build();
        model.setId(dto.getId());
        return model;
    }

    @Override
    public OfficeDTO convertModelToDto(Office office) {
        DirectorDTO directorDTO = directorConverter.convertModelToDtoOnlyDirector(office.getDirector());
        OfficeDTO dto = OfficeDTO.builder()
                .officeNumber(office.getOfficeNumber())
                .address(office.getAddress())
                .director(directorDTO)
                .build();
        dto.setId(office.getId());
        return dto;
    }

    public Office convertDtoToModelOnlyOffice(OfficeDTO dto) {
        Office model = Office.builder()
                .officeNumber(dto.getOfficeNumber())
                .address(dto.getAddress())
                .build();
        model.setId(dto.getId());
        return model;
    }

    public OfficeDTO convertModelToDtoOnlyOffice(Office office) {
        OfficeDTO dto = OfficeDTO.builder()
                .officeNumber(office.getOfficeNumber())
                .address(office.getAddress())
                .build();
        dto.setId(office.getId());
        return dto;
    }
}
