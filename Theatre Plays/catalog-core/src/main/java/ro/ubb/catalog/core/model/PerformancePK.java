package ro.ubb.catalog.core.model;

import lombok.*;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class PerformancePK implements Serializable {
  private Play play;
  private Actor actor;
}
