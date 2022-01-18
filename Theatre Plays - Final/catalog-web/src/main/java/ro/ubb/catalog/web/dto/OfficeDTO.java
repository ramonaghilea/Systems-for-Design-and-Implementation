package ro.ubb.catalog.web.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(callSuper = true, exclude = {"director"})
@ToString(callSuper = true, exclude = {"director"})
@Builder
public class OfficeDTO extends BaseDTO {
    private String officeNumber;
    private String address;
    private DirectorDTO director;
}
