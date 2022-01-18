package ro.ubb.catalog.core.repository.director;

import org.springframework.stereotype.Component;
import ro.ubb.catalog.core.model.Director;

import java.util.List;

public interface DirectorRepositoryCustom {
    List<Director> findAllWithOffices();
    List<Director> findAllWithOfficeAndPlays();
}
