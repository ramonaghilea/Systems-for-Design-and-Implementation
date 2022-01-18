package ro.ubb.catalog.core.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@IdClass(RoomPropsPK.class)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@Builder
public class RoomProps implements Serializable {
    @Id
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
//  @ManyToOne
//  @MapsId("play")
    @JoinColumn(name = "room_id")
    private TheatreRoom theatreRoom;

    @Id
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
//  @ManyToOne
//  @MapsId("actor")
    @JoinColumn(name = "props_id")
    private Props props;

    @Column(name = "usedItems")
    private Integer usedItems;

    @Override
    public String toString() {
        return "RoomProps {" +
                "theatre room = " + theatreRoom.getId() + " | " +
                "props = " + props.getId() + " | " +
                "number used items = " + usedItems + "}";
    }
}
