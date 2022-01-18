package ro.ubb.catalog.core.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

@MappedSuperclass
@AllArgsConstructor
@NoArgsConstructor
@Data
public abstract class BaseEntity <ID extends Serializable> implements Serializable {

    @Id
    @GeneratedValue
    private ID id;

  /**
   * @return the id of the current entity.
   */
    public ID getId() {
        return id;
    }


  /**
   * Modifies the id of the entity.
   *
   * @param id - the new id.

   */
    public void setId(ID id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "BaseEntity{" +
                "id=" + id +
                '}';
    }
}
