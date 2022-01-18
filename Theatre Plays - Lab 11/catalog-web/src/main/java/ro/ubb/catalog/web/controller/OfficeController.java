package ro.ubb.catalog.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ro.ubb.catalog.core.service.OfficeService;
import ro.ubb.catalog.web.converter.OfficeConverter;
import ro.ubb.catalog.web.dto.OfficeDTO;

import java.util.Set;

@RestController
public class OfficeController {
    public static final Logger log = LoggerFactory.getLogger(OfficeController.class);

    @Autowired
    private OfficeService officeService;

    @Autowired
    private OfficeConverter officeConverter;

    @RequestMapping(value = "/offices")
    Set<OfficeDTO> getAllOffices()
    {
        log.trace("> getAllOffices - method entered.");
        var result =  officeConverter.convertModelsToDtos(officeService.getAllOffices());
        log.trace("> getAllOffices - result = {}.", result);
        return result;
    }
}
