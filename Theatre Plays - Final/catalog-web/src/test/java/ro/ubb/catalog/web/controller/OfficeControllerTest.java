package ro.ubb.catalog.web.controller;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import ro.ubb.catalog.core.model.Office;
import ro.ubb.catalog.core.service.OfficeService;
import ro.ubb.catalog.web.converter.OfficeConverter;
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

public class OfficeControllerTest {

    private MockMvc mockMvc;

    @InjectMocks
    private OfficeController officeController;

    @Mock
    private OfficeService officeService;

    @Mock
    private OfficeConverter officeConverter;

    private Office office1;
    private Office office2;
    private OfficeDTO officeDTO1;
    private OfficeDTO officeDTO2;


    private OfficeDTO createOfficeDTO(Office office) {
        OfficeDTO officeDTO = OfficeDTO.builder()
                .officeNumber(office.getOfficeNumber())
                .address(office.getAddress())
                .build();
        officeDTO.setId(office.getId());

        return officeDTO;
    }

    private void initData() {
        office1 = Office.builder().officeNumber("23A").address("street 34").build();
        office1.setId(1L);
        office2 = Office.builder().officeNumber("25B").address("street 25").build();
        office2.setId(2L);

        officeDTO1 = createOfficeDTO(office1);
        officeDTO2 = createOfficeDTO(office2);
    }

    @Before
    public void setup() throws Exception {
        initMocks(this);

        this.mockMvc = MockMvcBuilders
                .standaloneSetup(officeController)
                .build();

        initData();
    }


    @Test
    public void getAllOffices() throws Exception {
        List<Office> offices = Arrays.asList(office1, office2);
        Set<OfficeDTO> officeDTOS =
                new HashSet<>(Arrays.asList(officeDTO1, officeDTO2));

        when(officeService.getAllOffices()).thenReturn(offices);
        when(officeConverter.convertModelsToDtos(offices)).thenReturn(officeDTOS);

        ResultActions resultActions = mockMvc
                .perform(MockMvcRequestBuilders.get("/offices"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].officeNumber", anyOf(is("23A"), is("25B"))))
                .andExpect(jsonPath("$[1].address", anyOf(is("street 34"), is("street 25"))));

        String result = resultActions.andReturn().getResponse().getContentAsString();
        System.out.println(result);

        verify(officeService, times(1)).getAllOffices();
        verify(officeConverter, times(1)).convertModelsToDtos(offices);
        verifyNoMoreInteractions(officeService, officeConverter);
    }
}
