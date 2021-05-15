package ro.ubb.catalog.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import ro.ubb.catalog.core.service.PlayService;
import ro.ubb.catalog.core.service.PropsService;
import ro.ubb.catalog.web.converter.PlayConverter;

@RestController
public class PropsController {
    public static final Logger log = LoggerFactory.getLogger(PropsController.class);

    @Autowired
    private PropsService propsService;
}
