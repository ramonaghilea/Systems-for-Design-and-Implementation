package ro.ubb.catalog.web.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import ro.ubb.catalog.core.model.Director;
import ro.ubb.catalog.core.model.Office;
import ro.ubb.catalog.core.service.DirectorService;
import ro.ubb.catalog.web.converter.DirectorConverter;
import ro.ubb.catalog.web.dto.DirectorDTO;
import ro.ubb.catalog.web.dto.OfficeDTO;


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

public class DirectorControllerTest {

    private MockMvc mockMvc;

    @InjectMocks
    private DirectorController directorController;

    @Mock
    private DirectorService directorService;

    @Mock
    private DirectorConverter directorConverter;

    private Office office1;
    private Office office2;
    private Office office3;

    private Director director1;
    private Director director2;
    private Director director3;
    private DirectorDTO directorDTO1;
    private DirectorDTO directorDTO2;
    private DirectorDTO directorDTO3;


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

    private void initData() {
        office1 = Office.builder().officeNumber("23A").address("street 34").build();
        office1.setId(1L);

        director1 = Director.builder()
                .name("Helen")
                .age(30)
                .gender("female")
                .office(office1)
                .build();
        director1.setId(3L);

        office2 = Office.builder().officeNumber("25B").address("street 25").build();
        office2.setId(2L);

        director2 = Director.builder()
                .name("Chris")
                .age(27)
                .gender("male")
                .office(office2)
                .build();
        director2.setId(4L);

        directorDTO1 = createDirectorDTO(director1);
        directorDTO2 = createDirectorDTO(director2);

        office3 = new Office("30C", "street 30");
        office3.setId(5L);

        director3 = new Director("Mara", 20, "female", office3);
        director3.setId(6L);
        directorDTO3 = createDirectorDTO(director3);
    }

    private String toJsonString(DirectorDTO directorDTO) {
        try {
            return new ObjectMapper().writeValueAsString(directorDTO);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Before
    public void setup() throws Exception {
        initMocks(this);

        this.mockMvc = MockMvcBuilders
                .standaloneSetup(directorController)
                .build();

        initData();
    }

    @Test
    public void getAllDirectors() throws Exception {
        List<Director> directors = Arrays.asList(director1, director2);
        Set<DirectorDTO> directorDTOS = new HashSet<>(
                Arrays.asList(directorDTO1, directorDTO2));

        // configure mock objects to return test data when a method is invoked
        when(directorService.getAllDirectors()).thenReturn(directors);
        when(directorConverter.convertModelsToDtos(directors)).thenReturn(directorDTOS);

        // invoke the HTTP get request
        ResultActions resultActions = mockMvc
                .perform(MockMvcRequestBuilders.get("/directors"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].name", anyOf(is("Helen"), is("Chris"))))
                .andExpect(jsonPath("$[1].age", anyOf(is(30), is(27))))
                .andExpect(jsonPath("$[0].gender", anyOf(is("female"), is("male"))));

        String result = resultActions.andReturn().getResponse().getContentAsString();
        System.out.println(result);

        // verify that the methods are invoked exactly once
        // verify that after the response, no more interactions are made
        verify(directorService, times(1)).getAllDirectors();
        verify(directorConverter, times(1)).convertModelsToDtos(directors);
        verifyNoMoreInteractions(directorService, directorConverter);
    }

    @Test
    public void getAllDirectorsReport() throws Exception {
        List<Director> directors = Arrays.asList(director1, director2);
        Set<DirectorDTO> directorDTOS = new HashSet<>(
                Arrays.asList(directorDTO1, directorDTO2));

        when(directorService.getAllDirectorsReport()).thenReturn(directors);
        when(directorConverter.convertModelsToDtos(directors)).thenReturn(directorDTOS);

        ResultActions resultActions = mockMvc
                .perform(MockMvcRequestBuilders.get("/directors/report"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].name", anyOf(is("Helen"), is("Chris"))))
                .andExpect(jsonPath("$[1].age", anyOf(is(30), is(27))))
                .andExpect(jsonPath("$[0].gender", anyOf(is("female"), is("male"))))
                .andExpect(jsonPath("$[1].office.officeNumber", anyOf(is("23A"), is("25B"))));

        String result = resultActions.andReturn().getResponse().getContentAsString();
        System.out.println(result);

        verify(directorService, times(1)).getAllDirectorsReport();
        verify(directorConverter, times(1)).convertModelsToDtos(directors);
        verifyNoMoreInteractions(directorService, directorConverter);
    }

    @Test
    public void addDirector() throws Exception {
        when(directorService.addDirector(director3)).thenReturn(director3);
        when(directorConverter.convertModelToDto(any(Director.class)))
                .thenReturn(directorDTO3);
        when(directorConverter.convertDtoToModel(any(DirectorDTO.class)))
                .thenReturn(director3);

        ResultActions resultActions = mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/directors", directorDTO3)
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(toJsonString(directorDTO3)))

                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.name", is("Mara")))
                .andExpect(jsonPath("$.age", is(20)))
                .andExpect(jsonPath("$.gender", is("female")));

        verify(directorService, times(1)).addDirector(director3);
        verify(directorConverter, times(1)).convertModelToDto(any(Director.class));
        verify(directorConverter, times(1)).convertDtoToModel(any(DirectorDTO.class));
        verifyNoMoreInteractions(directorService, directorConverter);
    }

    @Test
    public void deleteDirector() throws Exception {
//        when(directorService.deleteDirector(director1.getId()))
//                .thenReturn();

        ResultActions resultActions = mockMvc
                .perform(MockMvcRequestBuilders
                        .delete("/directors/{id}", director1.getId()))
                .andExpect(status().isOk());

        verify(directorService, times(1)).deleteDirector(director1.getId());
        verifyNoMoreInteractions(directorService);
    }
}
