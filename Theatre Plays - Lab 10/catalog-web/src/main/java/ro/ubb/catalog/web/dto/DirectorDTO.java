package ro.ubb.catalog.web.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
public class DirectorDTO extends BaseDTO {
  private String name;
  private Integer age;
  private String gender;
}
