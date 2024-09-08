package org.alpagu.sinemaotomasyonu.Tests.ServiceTests;

import org.alpagu.sinemaotomasyonu.Business.Concretes.TheatreServiceImpl;
import org.alpagu.sinemaotomasyonu.DataAccess.Abstracts.TheatreRepository;
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

class TheatreServiceImplTest {

    @InjectMocks
    private TheatreServiceImpl theatreService;

    @Mock
    private TheatreRepository theatreRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSaveTheatre() {
        Theatre theatre = new Theatre();
        theatre.setTheatreId("T123");
        theatre.setNameOfTheatre("Grand Theatre");

        when(theatreRepository.save(any(Theatre.class))).thenReturn(theatre);

        Theatre savedTheatre = theatreService.saveTheatre(theatre);

        assertNotNull(savedTheatre);
        assertEquals("T123", savedTheatre.getTheatreId());
        assertEquals("Grand Theatre", savedTheatre.getNameOfTheatre());
    }

    @Test
    void testGetTheatreById() {
        Theatre theatre = new Theatre();
        theatre.setTheatreId("T123");

        when(theatreRepository.findById("T123")).thenReturn(Optional.of(theatre));

        Theatre foundTheatre = theatreService.getTheatreById("T123");

        assertNotNull(foundTheatre);
        assertEquals("T123", foundTheatre.getTheatreId());
    }

    @Test
    void testDeleteTheatre() {
        doNothing().when(theatreRepository).deleteById("T123");

        theatreService.deleteTheatre("T123");

        verify(theatreRepository, times(1)).deleteById("T123");
    }
}
