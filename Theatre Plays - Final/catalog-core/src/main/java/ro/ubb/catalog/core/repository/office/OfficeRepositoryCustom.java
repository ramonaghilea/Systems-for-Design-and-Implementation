package ro.ubb.catalog.core.repository.office;

import ro.ubb.catalog.core.model.Office;

import java.util.List;

public interface OfficeRepositoryCustom {
    List<Office> findAllWithDirector();
    List<Office> findAllWithDirectorAndPlays();
}
