package ro.ubb.catalog.web.dto;

import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(callSuper = true, exclude = {"office"})
//@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true, exclude = {"office"})
@Builder
public class DirectorDTO extends BaseDTO {
  private String name;
  private Integer age;
  private String gender;
  private OfficeDTO office;
}
