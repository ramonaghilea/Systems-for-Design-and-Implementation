package ro.ubb.catalog.core.model;

import lombok.*;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

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

  @OneToMany(mappedBy = "actor", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
  private Set<Performance> performances = new HashSet<>();

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
