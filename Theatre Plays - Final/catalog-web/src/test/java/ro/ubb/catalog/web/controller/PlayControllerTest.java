package ro.ubb.catalog.web.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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
import ro.ubb.catalog.core.model.Director;
import ro.ubb.catalog.core.model.Office;
import ro.ubb.catalog.core.model.Play;
import ro.ubb.catalog.core.service.PlayService;
import ro.ubb.catalog.web.converter.PlayConverter;
import ro.ubb.catalog.web.dto.DirectorDTO;
import ro.ubb.catalog.web.dto.OfficeDTO;
import ro.ubb.catalog.web.dto.PlayDTO;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.hamcrest.CoreMatchers.anyOf;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

public class PlayControllerTest {

    private MockMvc mockMvc;

    @InjectMocks
    private PlayController playController;

    @Mock
    private PlayService playService;

    @Mock
    private PlayConverter playConverter;

    private Office office;
    private Director director;
    private Play play;

    private PlayDTO playDTO;


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

        playDTO = createPlayDTO(play);
    }

    private String toJsonString(PlayDTO playDTO) {
        try {
            return new ObjectMapper().writeValueAsString(playDTO);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Before
    public void setup() throws Exception {
        initMocks(this);

        this.mockMvc = MockMvcBuilders
                .standaloneSetup(playController)
                .build();

        initData();
    }

    @Test
    public void getAllPlays() throws Exception {
        List<Play> plays = Arrays.asList(play);
        Set<PlayDTO> playDTOS = new HashSet<>(
                Arrays.asList(playDTO));

        // configure mock objects to return test data when a method is invoked
        when(playService.getAllPlays()).thenReturn(plays);
        when(playConverter.convertModelsToDtos(plays)).thenReturn(playDTOS);

        // invoke the HTTP get request
        ResultActions resultActions = mockMvc
                .perform(MockMvcRequestBuilders.get("/plays"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].playName", is("Romeo and Juliet")))
                .andExpect(jsonPath("$[0].duration", is(120)))
                .andExpect(jsonPath("$[0].director.name", is("Helen")));

        String result = resultActions.andReturn().getResponse().getContentAsString();
        System.out.println(result);

        // verify that the methods are invoked exactly once
        // verify that after the response, no more interactions are made
        verify(playService, times(1)).getAllPlays();
        verify(playConverter, times(1)).convertModelsToDtos(plays);
        verifyNoMoreInteractions(playService, playConverter);
    }

    @Test
    public void getPlay() throws Exception {
        when(playService.getPlay(3L)).thenReturn(play);
        when(playConverter.convertModelToDto(any(Play.class)))
                .thenReturn(playDTO);

        ResultActions resultActions = mockMvc
                .perform(MockMvcRequestBuilders.get("/plays/details/{id}", 3L))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.playName", is("Romeo and Juliet")))
                .andExpect(jsonPath("$.duration", is(120)))
                .andExpect(jsonPath("$.director.name", is("Helen")));

        String result = resultActions.andReturn().getResponse().getContentAsString();
        System.out.println(result);

        verify(playService, times(1)).getPlay(3L);
        verifyNoMoreInteractions(playService);
    }

    @Test
    public void getPlay_Fail() throws Exception {
        when(playService.getPlay(10L)).thenReturn(null);

        ResultActions resultActions = mockMvc
                .perform(MockMvcRequestBuilders.get("/plays/details/{id}", 10L))
                .andExpect(status().isNotFound());

        verify(playService, times(1)).getPlay(10L);
        verifyNoMoreInteractions(playService);
    }

    @Test
    public void addPlay() throws Exception {
        when(playService.addPlay(play)).thenReturn(play);
        when(playConverter.convertModelToDto(any(Play.class)))
                .thenReturn(playDTO);
        when(playConverter.convertDtoToModel(any(PlayDTO.class)))
                .thenReturn(play);

        ResultActions resultActions = mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/plays")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(toJsonString(playDTO)))

                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.playName", is("Romeo and Juliet")))
                .andExpect(jsonPath("$.duration", is(120)))
                .andExpect(jsonPath("$.director.name", is("Helen")));

        verify(playService, times(1)).addPlay(play);
        verify(playConverter, times(1)).convertModelToDto(any(Play.class));
        verify(playConverter, times(1)).convertDtoToModel(any(PlayDTO.class));
        verifyNoMoreInteractions(playService, playConverter);
    }

    @Test
    public void deletePlay() throws Exception {
        ResultActions resultActions = mockMvc
                .perform(MockMvcRequestBuilders
                        .delete("/plays/{id}", play.getId()))
                .andExpect(status().isOk());

        verify(playService, times(1)).deletePlay(play.getId());
        verifyNoMoreInteractions(playService);
    }

    @Test
    public void updatePlay() throws Exception {
        when(playService.updatePlay(play)).thenReturn(play);
        when(playConverter.convertModelToDto(any(Play.class)))
                .thenReturn(playDTO);
        when(playConverter.convertDtoToModel(any(PlayDTO.class)))
                .thenReturn(play);

        ResultActions resultActions = mockMvc
                .perform(MockMvcRequestBuilders
                        .put("/plays/{id}", play.getId())
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(toJsonString(playDTO)))

                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.playName", is("Romeo and Juliet")))
                .andExpect(jsonPath("$.duration", is(120)))
                .andExpect(jsonPath("$.director.name", is("Helen")));

        verify(playService, times(1)).updatePlay(play);
        verify(playConverter, times(1)).convertModelToDto(any(Play.class));
        verify(playConverter, times(1)).convertDtoToModel(any(PlayDTO.class));
        verifyNoMoreInteractions(playService, playConverter);
    }
}
