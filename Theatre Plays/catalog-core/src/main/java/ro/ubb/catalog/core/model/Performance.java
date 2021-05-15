package ro.ubb.catalog.core.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@IdClass(PerformancePK.class)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Performance implements Serializable {

//  @EmbeddedId
//  PerformancePK PK;

  @Id
  @ManyToOne(optional = false, fetch = FetchType.EAGER)
//  @ManyToOne
//  @MapsId("play")
  @JoinColumn(name = "play_id", referencedColumnName = "id")
  private Play play;

  @Id
  @ManyToOne(optional = false, fetch = FetchType.EAGER)
//  @ManyToOne
//  @MapsId("actor")
  @JoinColumn(name = "actor_id", referencedColumnName = "id")
  private Actor actor;

  @Column(name = "role")
  private String role;

  @Override
  public String toString() {
    return "Performance {" +
            "play = " + play.getId() + " | " +
            "actor = " + actor.getId() + " | " +
            "role = " + role + "}";
  }
}
