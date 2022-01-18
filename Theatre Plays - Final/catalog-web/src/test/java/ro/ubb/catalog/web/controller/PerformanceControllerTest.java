package ro.ubb.catalog.web.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jdk.internal.perf.Perf;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import ro.ubb.catalog.core.model.*;
import ro.ubb.catalog.core.service.PerformanceService;
import ro.ubb.catalog.web.converter.PerformanceConverter;
import ro.ubb.catalog.web.dto.*;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

public class PerformanceControllerTest {

    private MockMvc mockMvc;

    @InjectMocks
    private PerformanceController performanceController;

    @Mock
    private PerformanceService performanceService;

    @Mock
    private PerformanceConverter performanceConverter;

    private Actor actor;
    private Office office;
    private Director director;
    private Play play;
    private Performance performance;
    private PerformanceDTO performanceDTO;


    private ActorDTO createActorDTO(Actor actor)
    {
        ActorDTO actorDTO = ActorDTO.builder()
                .name(actor.getName())
                .age(actor.getAge())
                .gender(actor.getGender())
                .build();
        actorDTO.setId(actor.getId());

        return actorDTO;
    }

    private OfficeDTO createOfficeDTO(Office office) {
        OfficeDTO officeDTO = OfficeDTO.builder()
                .officeNumber(office.getOfficeNumber())
                .address(office.getAddress())
                .build();
        officeDTO.setId(office.getId());

        return officeDTO;
    }

    private DirectorDTO createDirectorDTO(Director director) {
        OfficeDTO officeDTO = createOfficeDTO(director.getOffice());

        DirectorDTO directorDTO = DirectorDTO.builder()
                .name(director.getName())
                .age(director.getAge())
                .gender(director.getGender())
                .office(officeDTO)
                .build();
        directorDTO.setId(director.getId());

        return directorDTO;
    }

    private PlayDTO createPlayDTO(Play play)
    {
        DirectorDTO directorDTO = createDirectorDTO(play.getDirector());

        PlayDTO dto = PlayDTO.builder()
                .playName(play.getPlayName())
                .duration(play.getDuration())
                .director(directorDTO)
                .build();
        dto.setId(play.getId());

        return dto;
    }

    private PerformanceDTO createPerformanceDTO(Performance performance)
    {
        PlayDTO playDTO = createPlayDTO(performance.getPlay());
        ActorDTO actorDTO = createActorDTO(performance.getActor());

        PerformanceDTO performanceDTO = PerformanceDTO.builder()
                .play(playDTO)
                .actor(actorDTO)
                .role(performance.getRole())
                .build();

        return performanceDTO;
    }

    private void initData() {
        office = Office.builder().officeNumber("23A").address("street 34").build();
        office.setId(1L);

        director = Director.builder()
                .name("Helen")
                .age(30)
                .gender("female")
                .office(office)
                .build();
        director.setId(2L);

        play = Play.builder()
                .playName("Romeo and Juliet")
                .duration(120L)
                .director(director)
                .build();
        play.setId(3L);

        actor = Actor.builder()
                .name("Anna")
                .age(18)
                .gender("female")
                .build();
        actor.setId(4L);

        performance = Performance.builder()
                .play(play)
                .actor(actor)
                .role("Juliet")
                .build();

        performanceDTO = createPerformanceDTO(performance);
    }

    private String toJsonString(PerformanceDTO performanceDTO) {
        try {
            return new ObjectMapper().writeValueAsString(performanceDTO);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Before
    public void setup() throws Exception {
        initMocks(this);

        this.mockMvc = MockMvcBuilders
                .standaloneSetup(performanceController)
                .build();

        initData();
    }

    @Test
    public void getAllPerformances() throws Exception {
        List<Performance> performances = Arrays.asList(performance);
        Set<PerformanceDTO> performanceDTOS = new HashSet<>(
                Arrays.asList(performanceDTO));

        when(performanceService.getAllPerformances()).thenReturn(performances);
        when(performanceConverter.convertModelsToDtos(performances)).thenReturn(performanceDTOS);

        ResultActions resultActions = mockMvc
                .perform(MockMvcRequestBuilders.get("/performances"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].play.playName", is("Romeo and Juliet")))
                .andExpect(jsonPath("$[0].actor.name", is("Anna")))
                .andExpect(jsonPath("$[0].role", is("Juliet")));

        String result = resultActions.andReturn().getResponse().getContentAsString();
        System.out.println(result);

        verify(performanceService, times(1)).getAllPerformances();
        verify(performanceConverter, times(1)).convertModelsToDtos(performances);
        verifyNoMoreInteractions(performanceService, performanceConverter);
    }

    @Test
    public void getPerformance() throws Exception {
        when(performanceService.getPerformance(3L, 4L)).thenReturn(performance);
        when(performanceConverter.convertModelToDto(any(Performance.class)))
                .thenReturn(performanceDTO);

        ResultActions resultActions = mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/performances/details/{playId}/{actorId}", 3L, 4L))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.play.playName", is("Romeo and Juliet")))
                .andExpect(jsonPath("$.actor.name", is("Anna")))
                .andExpect(jsonPath("$.role", is("Juliet")));

        String result = resultActions.andReturn().getResponse().getContentAsString();
        System.out.println(result);

        verify(performanceService, times(1)).getPerformance(3L, 4L);
        verify(performanceConverter, times(1)).convertModelToDto(any(Performance.class));
        verifyNoMoreInteractions(performanceService, performanceConverter);
    }

    @Test
    public void addPerformance() throws Exception {
        when(performanceService.addPerformance(performance))
                .thenReturn(performance);
        when(performanceConverter.convertModelToDto(any(Performance.class)))
                .thenReturn(performanceDTO);
        when(performanceConverter.convertDtoToModel(any(PerformanceDTO.class)))
                .thenReturn(performance);

        ResultActions resultActions = mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/performances")
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(toJsonString(performanceDTO)))

                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.play.playName", is("Romeo and Juliet")))
                .andExpect(jsonPath("$.actor.name", is("Anna")))
                .andExpect(jsonPath("$.role", is("Juliet")));

        verify(performanceService, times(1)).addPerformance(performance);
        verify(performanceConverter, times(1)).convertModelToDto(any(Performance.class));
        verify(performanceConverter, times(1)).convertDtoToModel(any(PerformanceDTO.class));
        verifyNoMoreInteractions(performanceService, performanceConverter);
    }

    @Test
    public void deletePerformance() throws Exception {
        ResultActions resultActions = mockMvc
                .perform(MockMvcRequestBuilders
                        .delete("/performances/{playId}/{actorId}",
                                play.getId(), actor.getId()))
                .andExpect(status().isOk());

        verify(performanceService, times(1))
                .deletePerformance(play.getId(), actor.getId());
        verifyNoMoreInteractions(performanceService);
    }

    @Test
    public void updatePerformance() throws Exception {
        when(performanceService.updatePerformance(performance))
                .thenReturn(performance);
        when(performanceConverter.convertModelToDto(any(Performance.class)))
                .thenReturn(performanceDTO);
        when(performanceConverter.convertDtoToModel(any(PerformanceDTO.class)))
                .thenReturn(performance);

        ResultActions resultActions = mockMvc
                .perform(MockMvcRequestBuilders
                        .put("/performances/{playId}/{actorId}",
                                play.getId(), actor.getId())
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(toJsonString(performanceDTO)))

                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.play.playName", is("Romeo and Juliet")))
                .andExpect(jsonPath("$.actor.name", is("Anna")))
                .andExpect(jsonPath("$.role", is("Juliet")));

        verify(performanceService, times(1)).updatePerformance(performance);
        verify(performanceConverter, times(1)).convertModelToDto(any(Performance.class));
        verify(performanceConverter, times(1)).convertDtoToModel(any(PerformanceDTO.class));
        verifyNoMoreInteractions(performanceService, performanceConverter);
    }
}
