package ro.ubb.catalog.core.model;

import lombok.*;

import javax.persistence.*;

@NamedEntityGraphs({
        @NamedEntityGraph(name = "officeWithDirector",
                attributeNodes = @NamedAttributeNode(value = "director")),

        @NamedEntityGraph(name = "officeWithDirectorAndPlays",
                attributeNodes = @NamedAttributeNode(value = "director",
                        subgraph = "directorWithPlays"),
        subgraphs = @NamedSubgraph(name = "directorWithPlays",
                attributeNodes = @NamedAttributeNode(value = "plays")))
})



@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(callSuper = true, exclude = {"director"})
@ToString(callSuper = true, exclude = {"director"})
@Builder
public class Office extends BaseEntity<Long> {
    private String officeNumber;
    private String address;

    @OneToOne(fetch = FetchType.LAZY)
    private Director director;

    public Office(String officeNumber, String address)
    {
        this.officeNumber = officeNumber;
        this.address = address;
    }

//    public Office(String officeNumber, String address, Director director)
//    {
//        this.officeNumber = officeNumber;
//        this.address = address;
//        this.director = director;
//    }

    public Integer getDirectorNumberPlays() {
        return director.getPlays() == null ? 0 : director.getPlays().size();
    }
}
