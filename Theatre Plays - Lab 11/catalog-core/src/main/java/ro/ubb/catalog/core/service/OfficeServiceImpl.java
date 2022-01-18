package ro.ubb.catalog.core.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.ubb.catalog.core.model.Office;
import ro.ubb.catalog.core.model.Play;
import ro.ubb.catalog.core.repository.OfficeRepository;

import java.util.List;

@Service
public class OfficeServiceImpl implements OfficeService {
    public static final Logger log = LoggerFactory.getLogger(OfficeServiceImpl.class);

    @Autowired
    private OfficeRepository officeRepository;

    @Override
    public List<Office> getAllOffices() {
        log.trace(" > getAllOffices - method was entered.");
        List<Office> offices =  this.officeRepository.findAll();
        log.trace(" > getAllOffices - result = {}", offices);
        return offices;
    }
}
