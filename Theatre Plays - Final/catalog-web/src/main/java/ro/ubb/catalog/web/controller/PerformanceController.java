package ro.ubb.catalog.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import ro.ubb.catalog.core.model.Performance;
import ro.ubb.catalog.core.service.PerformanceService;
import ro.ubb.catalog.core.service.PlayService;
import ro.ubb.catalog.core.service.PlayServiceImpl;
import ro.ubb.catalog.web.converter.ActorConverter;
import ro.ubb.catalog.web.converter.PerformanceConverter;
import ro.ubb.catalog.web.converter.PlayConverter;
import ro.ubb.catalog.web.dto.DirectorDTO;
import ro.ubb.catalog.web.dto.PerformanceDTO;
import ro.ubb.catalog.web.dto.PlayDTO;

import java.util.Set;

@RestController
public class PerformanceController {
    public static final Logger log = LoggerFactory.getLogger(PerformanceController.class);

    @Autowired
    private PerformanceService performanceService;

    @Autowired
    private PerformanceConverter performanceConverter;

//    @Autowired
//    private PlayConverter playConverter;
//
//    @Autowired
//    private ActorConverter actorConverter;

    @RequestMapping(value = "/performances")
    Set<PerformanceDTO> getAllPerformances()
    {
        log.trace("> getAllPerformances - method entered.");
        Set<PerformanceDTO> result =  performanceConverter.convertModelsToDtos(performanceService.getAllPerformances());
        log.trace("> getAllPerformances - result = {}.", result);
        return result;
    }

    @RequestMapping(value = "/performances/details/{playId}/{actorId}")
    PerformanceDTO getPerformance(@PathVariable Long playId,
                    @PathVariable Long actorId)
    {
        log.trace("> getPerformance - method entered. playId = {} actorId = {}", playId, actorId);
        PerformanceDTO result =  performanceConverter.convertModelToDto(performanceService.getPerformance(playId, actorId));
        log.trace("> getPerformance - result = {}.", result);
        return result;
    }

    @RequestMapping(value = "/performances", method = RequestMethod.POST)
    PerformanceDTO addPerformance(@RequestBody PerformanceDTO performanceDTO){
        log.trace("> addPerformance - method entered. performanceDto = {}", performanceDTO);
        Performance performance = performanceConverter.convertDtoToModel(performanceDTO);
        log.trace("> addPerformance - after DTO -> Model conversion performance = {}", performance);
        Performance result = performanceService.addPerformance(performance);
        log.trace("> addPerformance - result = {}", result);
        PerformanceDTO resultModel = performanceConverter.convertModelToDto(result);

        log.trace("> addPerformance - after Model -> DTO conversion performanceModel = {}", resultModel);
        return resultModel;
    }

    @RequestMapping(value = "/performances/{playId}/{actorId}", method = RequestMethod.DELETE)
    ResponseEntity<?> deletePerformance(@PathVariable Long playId,
                                        @PathVariable Long actorId) {
        log.trace("> deletePerformance - method entered. playId = {} actorId = {}", playId, actorId);
        performanceService.deletePerformance(playId, actorId);
        log.trace("> deletePerformance - method finished.");
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/performances/{playId}/{actorId}", method = RequestMethod.PUT)
    PerformanceDTO updatePerformance(@PathVariable Long playId,
                       @PathVariable Long actorId,
                       @RequestBody PerformanceDTO dto) {
        log.trace("> updatePerformance - method entered. performanceDTO = {}", dto);
        PerformanceDTO result =  performanceConverter.convertModelToDto(
                performanceService.updatePerformance(
                        performanceConverter.convertDtoToModel(dto)
                ));
        log.trace("> updatePerformance - result of update: result = {}", result);
        return result;
    }
}
