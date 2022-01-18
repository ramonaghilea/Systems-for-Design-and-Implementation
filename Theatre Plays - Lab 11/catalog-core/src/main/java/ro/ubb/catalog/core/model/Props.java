package ro.ubb.catalog.core.model;

import lombok.*;

import javax.persistence.Entity;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
//@EqualsAndHashCode(callSuper = true, exclude = {"director", "performances"})
@EqualsAndHashCode
//@ToString(callSuper = true, exclude = {"director", "performances"})
@ToString
@Builder
public class Props extends BaseEntity<Long> {
    private String name;
    private Integer numberItems;
}
