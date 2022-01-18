package ro.ubb.catalog.core.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.ubb.catalog.core.model.Director;
import ro.ubb.catalog.core.model.Office;
import ro.ubb.catalog.core.repository.office.OfficeRepository;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OfficeServiceImpl implements OfficeService {
    public static final Logger log = LoggerFactory.getLogger(OfficeServiceImpl.class);

    @Autowired
    private OfficeRepository officeRepository;

    @Override
    public List<Office> getAllOffices() {
        log.trace(" > getAllOffices - method was entered.");

//        List<Director> directors = this.directorRepository.findAllWithOffices();
//        List<Office> offices = directors.stream()
//                .map(director -> director.getOffice())
//                .collect(Collectors.toList());

        List<Office> offices = this.officeRepository.findAllWithDirectorQuery();
//        List<Office> offices = this.officeRepository.findAllWithDirector();

        log.trace(" > getAllOffices - result = {}", offices);
        return offices;
    }

    @Override
    public List<Office> getAllOfficesReport() {
        log.trace(" > getAllOfficesReport - method was entered.");

//        List<Director> directors = this.directorRepository.findAllWithOffices();
//        List<Office> offices = directors.stream()
//                .map(director -> director.getOffice())
//                .collect(Collectors.toList());
//        List<Office> offices = this.officeRepository.findAllWithDirectorAndPlaysQuery();
        List<Office> offices = this.officeRepository.findAllWithDirectorAndPlays();

        List<Office> result = offices.stream()
                .sorted(Comparator.comparingInt(Office::getDirectorNumberPlays)
                        .reversed())
                .collect(Collectors.toList());

        log.trace(" > getAllOfficesReport - result = {}", offices);
        return result;
    }
}
