package ro.ubb.catalog.core.model;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@NamedEntityGraphs({
        @NamedEntityGraph(name = "actorWithPerformances",
                attributeNodes = @NamedAttributeNode(value = "performances")),

        @NamedEntityGraph(name = "actorWithPerformancesWithPlays",
                attributeNodes = @NamedAttributeNode(value = "performances", subgraph = "performancesWithPlay"),

                subgraphs = {
                @NamedSubgraph(name = "performancesWithPlay",
                                attributeNodes = @NamedAttributeNode(value = "play", subgraph = "playWithDirector")),
                        @NamedSubgraph(name = "playWithDirector",
                                        attributeNodes = @NamedAttributeNode(value = "director", subgraph = "directorWithOffice")),
                            @NamedSubgraph(name = "directorWithOffice",
                                            attributeNodes = @NamedAttributeNode(value = "office"))
                })
})

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(callSuper = true, exclude = {"performances"})
@ToString(callSuper = true, exclude = { "performances" })
@Builder
public class Actor extends BaseEntity<Long> {
  private String name;
  private Integer age;
  private String gender;

  @OneToMany(mappedBy = "actor", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
  private Set<Performance> performances;

  public Actor(String name, Integer age, String gender) {
    this.name = name;
    this.age = age;
    this.gender = gender;
  }

  public Set<Performance> getPerformances() {
    performances = performances == null ? new HashSet<>() : performances;
    return performances;
  }

  public Integer getNumberPerformances() {
    return performances == null ? 0 : performances.size();
  }

  public Set<Play> getPlays() {
    performances = performances == null ? new HashSet<>() : performances;

    return this.performances.stream()
      .map(Performance::getPlay)
      .collect(Collectors.toSet());
  }

  public Performance addPerformance(Performance performance) {
    this.performances.add(performance);

    return performance;
  }

  public void removePerformance(Long playId) {
    Performance performance = this.performances.stream()
            .filter(p -> p.getPlay().getId().equals(playId))
            .collect(Collectors.toList())
            .get(0);

    this.performances.remove(performance);
  }

  public Performance updatePerformance(Performance performance) {
    Performance performance1 = this.performances.stream()
            .filter(p -> p.getPlay().getId().equals(performance.getPlay().getId()))
            .collect(Collectors.toList())
            .get(0);

    performance1.setRole(performance.getRole());
    return performance1;
  }
}
