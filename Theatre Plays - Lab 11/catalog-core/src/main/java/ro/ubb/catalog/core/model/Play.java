package ro.ubb.catalog.core.model;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true, exclude = {"director", "performances"})
@ToString(callSuper = true, exclude = {"director", "performances"})
@Builder
public class Play extends BaseEntity<Long> {
    private String playName;
    private Long duration;

    @ManyToOne
    private Director director;

    @OneToMany(mappedBy = "play", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    private Set<Performance> performances = new HashSet<>();

    public Set<Performance> getPerformances() {
        performances = performances == null ? new HashSet<>() : performances;
        return performances;
    }

    public Integer getNumberPerformances() {
        return performances == null ? 0 : performances.size();
    }

    public Performance addPerformance(Performance performance) {
//        this.performances.add(new Performance(this, performance.getActor(), performance.getRole()));
//        performance.getActor().getPerformances().add(addPerformance);
        this.performances.add(performance);

        return performance;
    }

    public void removePerformance(Long actorId) {
        Performance performance = this.performances.stream()
                .filter(p -> p.getActor().getId().equals(actorId))
                .collect(Collectors.toList())
                .get(0);

//        performance.getActor().getPerformances().remove(performance);
        this.performances.remove(performance);
//        performance.getActor().getPerformances().remove(performance);
//        performance.setPlay(null);
//        performance.setActor(null);
    }

    public Performance updatePerformance(Performance performance) {
        Performance performance1 = this.performances.stream()
                .filter(p -> p.getActor().getId().equals(performance.getActor().getId()))
                .collect(Collectors.toList())
                .get(0);

        performance1.setRole(performance.getRole());
        return performance1;
    }
}
