package ro.ubb.catalog.core.model;

import lombok.*;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class RoomPropsPK implements Serializable {
    private TheatreRoom theatreRoom;
    private Props props;
}
