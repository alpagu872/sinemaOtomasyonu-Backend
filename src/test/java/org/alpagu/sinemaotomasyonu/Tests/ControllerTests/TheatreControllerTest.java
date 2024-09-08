package org.alpagu.sinemaotomasyonu.Tests.ControllerTests;

import org.alpagu.sinemaotomasyonu.Business.Abstracts.TheatreService;
import org.alpagu.sinemaotomasyonu.Api.Controllers.TheatreController;
import org.alpagu.sinemaotomasyonu.Entities.Concretes.Theatre;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(TheatreController.class)
class TheatreControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TheatreService theatreService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateTheatre() throws Exception {
        Theatre theatre = new Theatre();
        theatre.setTheatreId("T123");
        theatre.setNameOfTheatre("Grand Theatre");

        when(theatreService.saveTheatre(any(Theatre.class))).thenReturn(theatre);

        mockMvc.perform(post("/api/theatres")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"theatreId\":\"T123\",\"nameOfTheatre\":\"Grand Theatre\"}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.theatreId").value("T123"))
                .andExpect(jsonPath("$.nameOfTheatre").value("Grand Theatre"));
    }

    @Test
    void testGetTheatreById() throws Exception {
        Theatre theatre = new Theatre();
        theatre.setTheatreId("T123");
        theatre.setNameOfTheatre("Grand Theatre");

        when(theatreService.getTheatreById("T123")).thenReturn(theatre);

        mockMvc.perform(get("/api/theatres/T123")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.theatreId").value("T123"))
                .andExpect(jsonPath("$.nameOfTheatre").value("Grand Theatre"));
    }

    @Test
    void testGetAllTheatres() throws Exception {
        Theatre theatre1 = new Theatre();
        theatre1.setTheatreId("T123");
        theatre1.setNameOfTheatre("Grand Theatre");

        Theatre theatre2 = new Theatre();
        theatre2.setTheatreId("T124");
        theatre2.setNameOfTheatre("Mini Theatre");

        when(theatreService.getAllTheatres()).thenReturn(Arrays.asList(theatre1, theatre2));

        mockMvc.perform(get("/api/theatres")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].theatreId").value("T123"))
                .andExpect(jsonPath("$[1].theatreId").value("T124"));
    }

    @Test
    void testDeleteTheatre() throws Exception {
        doNothing().when(theatreService).deleteTheatre("T123");

        mockMvc.perform(delete("/api/theatres/T123")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }
}
