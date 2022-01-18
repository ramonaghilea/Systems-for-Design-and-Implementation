package ro.ubb.catalog.web.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(callSuper = true, exclude = {"director"})
@ToString(callSuper = true, exclude = {"director"})
@Builder
public class PlayDTO extends BaseDTO {
    private String playName;
    private Long duration;
    private DirectorDTO director;
}
