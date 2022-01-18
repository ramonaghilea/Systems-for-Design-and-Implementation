package ro.ubb.catalog.core.service;

import ro.ubb.catalog.core.model.Office;

import java.util.List;

public interface OfficeService {
    List<Office> getAllOffices();
    List<Office> getAllOfficesReport();
}
