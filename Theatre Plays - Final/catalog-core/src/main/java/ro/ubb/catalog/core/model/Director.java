package ro.ubb.catalog.core.model;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@NamedEntityGraphs({
        @NamedEntityGraph(name = "directorWithOffice",
                attributeNodes = @NamedAttributeNode(value = "office")),

        @NamedEntityGraph(name = "directorWithOfficeAndPlays",
                attributeNodes = {@NamedAttributeNode(value = "office"),
                        @NamedAttributeNode(value = "plays")})

//        @NamedEntityGraph(name = "directorWithOfficeAndPlays",
//                attributeNodes = {@NamedAttributeNode(value = "office"),
//                        @NamedAttributeNode(value = "plays", subgraph = "playsWithPerformances")},
//
//                subgraphs = @NamedSubgraph(name = "playsWithPerformances",
//                        attributeNodes = @NamedAttributeNode(value = "performances")))
})

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(callSuper = true, exclude = {"plays", "office"})
//@EqualsAndHashCode(callSuper = true, exclude = {"plays"})
@ToString(callSuper = true, exclude = { "plays", "office" })
@Builder
public class Director extends BaseEntity<Long> {
  private String name;
  private Integer age;
  private String gender;

  @OneToOne(mappedBy = "director", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
  protected Office office;

  @OneToMany(cascade = CascadeType.ALL, mappedBy = "director", fetch = FetchType.LAZY, orphanRemoval = true)
  private List<Play> plays;

  public Director(String name, Integer age, String gender) {
    this.name = name;
    this.age = age;
    this.gender = gender;
  }

  public Director(String name, Integer age, String gender, Office office) {
    this.name = name;
    this.age = age;
    this.gender = gender;
    this.office = office;
  }

  public Integer getNumberPlays() {
    return plays == null ? 0 : plays.size();
  }

  public void addPlay(Play play) {
    if(this.plays == null)
      this.plays = new ArrayList<>();
    this.plays.add(play);
  }

  public void removePlay(Play play) {
    this.plays.remove(play);
  }

  public Play updatePlay(Play play) {
    Play play1 = this.plays.stream()
            .filter(p -> p.getId().equals(play.getId()))
            .collect(Collectors.toList())
            .get(0);

    play1.setPlayName(play.getPlayName());
    play1.setDuration(play.getDuration());
    return play1;
  }
}

