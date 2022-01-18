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
import ro.ubb.catalog.core.model.Actor;
import ro.ubb.catalog.core.model.Director;
import ro.ubb.catalog.core.model.Play;
import ro.ubb.catalog.core.service.ActorService;
import ro.ubb.catalog.web.converter.ActorConverter;
import ro.ubb.catalog.web.dto.ActorDTO;
import ro.ubb.catalog.web.dto.DirectorDTO;
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

public class ActorControllerTest {

    private MockMvc mockMvc;

    @InjectMocks
    private ActorController actorController;

    @Mock
    private ActorService actorService;

    @Mock
    private ActorConverter actorConverter;

    private Actor actor1;
    private Actor actor2;
    private ActorDTO actorDTO1;
    private ActorDTO actorDTO2;


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

    private void initData() {
        actor1 = Actor.builder()
                .name("Anna")
                .age(18)
                .gender("female")
                .build();
        actor1.setId(1L);

        actor2 = Actor.builder()
                .name("Max")
                .age(20)
                .gender("male")
                .build();
        actor2.setId(2L);

        actorDTO1 = createActorDTO(actor1);
        actorDTO2 = createActorDTO(actor2);
    }

    private String toJsonString(ActorDTO actorDTO) {
        try {
            return new ObjectMapper().writeValueAsString(actorDTO);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Before
    public void setup() throws Exception {
        initMocks(this);

        this.mockMvc = MockMvcBuilders
                .standaloneSetup(actorController)
                .build();

        initData();
    }

    @Test
    public void getAllActors() throws Exception {
        List<Actor> actors = Arrays.asList(actor1, actor2);
        Set<ActorDTO> actorDTOS = new HashSet<>(
                Arrays.asList(actorDTO1, actorDTO2));

        // configure mock objects to return test data when a method is invoked
        when(actorService.getAllActors()).thenReturn(actors);
        when(actorConverter.convertModelsToDtos(actors)).thenReturn(actorDTOS);

        // invoke the HTTP get request
        ResultActions resultActions = mockMvc
                .perform(MockMvcRequestBuilders.get("/actors"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].name", anyOf(is("Anna"), is("Max"))))
                .andExpect(jsonPath("$[1].age", anyOf(is(18), is(20))))
                .andExpect(jsonPath("$[0].gender", anyOf(is("female"), is("male"))));

        String result = resultActions.andReturn().getResponse().getContentAsString();
        System.out.println(result);

        // verify that the methods are invoked exactly once
        // verify that after the response, no more interactions are made
        verify(actorService, times(1)).getAllActors();
        verify(actorConverter, times(1)).convertModelsToDtos(actors);
        verifyNoMoreInteractions(actorService, actorConverter);
    }

    @Test
    public void getAllActorsReport() throws Exception {
        List<Actor> actors = Arrays.asList(actor1, actor2);
        Set<ActorDTO> actorDTOS = new HashSet<>(
                Arrays.asList(actorDTO1, actorDTO2));

        // configure mock objects to return test data when a method is invoked
        when(actorService.getAllActorsReport()).thenReturn(actors);
        when(actorConverter.convertModelsToDtos(actors)).thenReturn(actorDTOS);

        // invoke the HTTP get request
        ResultActions resultActions = mockMvc
                .perform(MockMvcRequestBuilders.get("/actors/report"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].name", anyOf(is("Anna"), is("Max"))))
                .andExpect(jsonPath("$[1].age", anyOf(is(18), is(20))))
                .andExpect(jsonPath("$[0].gender", anyOf(is("female"), is("male"))));

        String result = resultActions.andReturn().getResponse().getContentAsString();
        System.out.println(result);

        // verify that the methods are invoked exactly once
        // verify that after the response, no more interactions are made
        verify(actorService, times(1)).getAllActorsReport();
        verify(actorConverter, times(1)).convertModelsToDtos(actors);
        verifyNoMoreInteractions(actorService, actorConverter);
    }

    @Test
    public void addActor() throws Exception {
        when(actorService.addActor(actor1)).thenReturn(actor1);
        when(actorConverter.convertModelToDto(any(Actor.class)))
                .thenReturn(actorDTO1);
        when(actorConverter.convertDtoToModel(any(ActorDTO.class)))
                .thenReturn(actor1);

        ResultActions resultActions = mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/actors")
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(toJsonString(actorDTO1)))

                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.name", is("Anna")))
                .andExpect(jsonPath("$.age", is(18)))
                .andExpect(jsonPath("$.gender", is("female")));

        verify(actorService, times(1)).addActor(actor1);
        verify(actorConverter, times(1)).convertModelToDto(any(Actor.class));
        verify(actorConverter, times(1)).convertDtoToModel(any(ActorDTO.class));
        verifyNoMoreInteractions(actorService, actorConverter);
    }

    @Test
    public void deleteActor() throws Exception {
        //        when(directorService.deleteDirector(director1.getId()))
//                .thenReturn();

        ResultActions resultActions = mockMvc
                .perform(MockMvcRequestBuilders
                        .delete("/actors/{id}", actor1.getId()))
                .andExpect(status().isOk());

        verify(actorService, times(1)).deleteActor(actor1.getId());
        verifyNoMoreInteractions(actorService);
    }

    @Test
    public void updateActor() throws Exception {
        when(actorService.updateActor(actor1)).thenReturn(actor1);
        when(actorConverter.convertModelToDto(any(Actor.class)))
                .thenReturn(actorDTO1);
        when(actorConverter.convertDtoToModel(any(ActorDTO.class)))
                .thenReturn(actor1);

        ResultActions resultActions = mockMvc
                .perform(MockMvcRequestBuilders
                        .put("/actors/{id}", actor1.getId(), actorDTO1)
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(toJsonString(actorDTO1)))

                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.name", is("Anna")));

        verify(actorService, times(1)).updateActor(actor1);
        verify(actorConverter, times(1)).convertModelToDto(any(Actor.class));
        verify(actorConverter, times(1)).convertDtoToModel(any(ActorDTO.class));
        verifyNoMoreInteractions(actorService, actorConverter);
    }
}
