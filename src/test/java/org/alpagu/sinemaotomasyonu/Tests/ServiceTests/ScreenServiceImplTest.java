package org.alpagu.sinemaotomasyonu.Tests.ServiceTests;

import org.alpagu.sinemaotomasyonu.Business.Concretes.ScreenServiceImpl;
import org.alpagu.sinemaotomasyonu.DataAccess.Abstracts.ScreenRepository;
import org.alpagu.sinemaotomasyonu.Entities.Concretes.Screen;
import org.alpagu.sinemaotomasyonu.Entities.Concretes.Theatre;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class ScreenServiceImplTest {

    @InjectMocks
    private ScreenServiceImpl screenService;

    @Mock
    private ScreenRepository screenRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSaveScreen() {
        Screen screen = new Screen();
        screen.setScreenId("S123");
        screen.setNoOfSeatsGold(100);
        screen.setNoOfSeatsSilver(150);

        Theatre theatre = new Theatre();
        theatre.setTheatreId("T123");
        screen.setTheatre(theatre);

        when(screenRepository.save(any(Screen.class))).thenReturn(screen);

        Screen savedScreen = screenService.saveScreen(screen);

        assertNotNull(savedScreen);
        assertEquals("S123", savedScreen.getScreenId());
        assertEquals(100, savedScreen.getNoOfSeatsGold());
    }

    @Test
    void testGetScreenById() {
        Screen screen = new Screen();
        screen.setScreenId("S123");

        when(screenRepository.findById("S123")).thenReturn(Optional.of(screen));

        Screen foundScreen = screenService.getScreenById("S123");

        assertNotNull(foundScreen);
        assertEquals("S123", foundScreen.getScreenId());
    }

    @Test
    void testDeleteScreen() {
        doNothing().when(screenRepository).deleteById("S123");

        screenService.deleteScreen("S123");

        verify(screenRepository, times(1)).deleteById("S123");
    }
}
