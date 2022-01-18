package ro.ubb.catalog.web.dto;

import lombok.*;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString(callSuper = true)
@Builder
public class PerformanceDTO implements Serializable {
    private PlayDTO play;
    private ActorDTO actor;
    private String role;
}
