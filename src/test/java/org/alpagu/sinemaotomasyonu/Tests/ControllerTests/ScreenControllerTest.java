package org.alpagu.sinemaotomasyonu.Tests.ControllerTests;

import org.alpagu.sinemaotomasyonu.Business.Abstracts.ScreenService;
import org.alpagu.sinemaotomasyonu.Api.Controllers.ScreenController;
import org.alpagu.sinemaotomasyonu.Entities.Concretes.Screen;
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

@WebMvcTest(ScreenController.class)
class ScreenControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ScreenService screenService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateScreen() throws Exception {
        Screen screen = new Screen();
        screen.setScreenId("S123");
        screen.setNoOfSeatsGold(100);
        screen.setNoOfSeatsSilver(150);

        when(screenService.saveScreen(any(Screen.class))).thenReturn(screen);

        mockMvc.perform(post("/api/screens")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"screenId\":\"S123\",\"noOfSeatsGold\":100,\"noOfSeatsSilver\":150}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.screenId").value("S123"))
                .andExpect(jsonPath("$.noOfSeatsGold").value(100));
    }

    @Test
    void testGetScreenById() throws Exception {
        Screen screen = new Screen();
        screen.setScreenId("S123");
        screen.setNoOfSeatsGold(100);
        screen.setNoOfSeatsSilver(150);

        when(screenService.getScreenById("S123")).thenReturn(screen);

        mockMvc.perform(get("/api/screens/S123")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.screenId").value("S123"))
                .andExpect(jsonPath("$.noOfSeatsGold").value(100));
    }

    @Test
    void testGetAllScreens() throws Exception {
        Screen screen1 = new Screen();
        screen1.setScreenId("S123");
        screen1.setNoOfSeatsGold(100);
        screen1.setNoOfSeatsSilver(150);

        Screen screen2 = new Screen();
        screen2.setScreenId("S124");
        screen2.setNoOfSeatsGold(120);
        screen2.setNoOfSeatsSilver(130);

        when(screenService.getAllScreens()).thenReturn(Arrays.asList(screen1, screen2));

        mockMvc.perform(get("/api/screens")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].screenId").value("S123"))
                .andExpect(jsonPath("$[1].screenId").value("S124"));
    }

    @Test
    void testDeleteScreen() throws Exception {
        doNothing().when(screenService).deleteScreen("S123");

        mockMvc.perform(delete("/api/screens/S123")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }
}
