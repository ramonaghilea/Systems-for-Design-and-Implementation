package ro.ubb.catalog.core.model;

import lombok.*;

import javax.persistence.Entity;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@Builder
public class Office extends BaseEntity<Long> {
    private String officeNumber;
    private String address;
}
