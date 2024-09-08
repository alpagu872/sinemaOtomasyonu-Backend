package org.alpagu.sinemaotomasyonu.Tests.ServiceTests;

import org.alpagu.sinemaotomasyonu.Business.Concretes.ShowServiceImpl;
import org.alpagu.sinemaotomasyonu.DataAccess.Abstracts.ShowRepository;
import org.alpagu.sinemaotomasyonu.Entities.Concretes.Movie;
import org.alpagu.sinemaotomasyonu.Entities.Concretes.Screen;
import org.alpagu.sinemaotomasyonu.Entities.Concretes.Show;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class ShowServiceImplTest {

    @InjectMocks
    private ShowServiceImpl showService;

    @Mock
    private ShowRepository showRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSaveShow() {
        Show show = new Show();
        show.setShowId("SH123");
        show.setShowTime(LocalTime.of(18, 30));
        show.setShowDate(LocalDate.of(2023, 12, 25));
        show.setSeatsRemainingGold(50);
        show.setSeatsRemainingSilver(75);

        Screen screen = new Screen();
        screen.setScreenId("S123");
        show.setScreen(screen);

        Movie movie = new Movie();
        movie.setMovieId("M123");
        show.setMovie(movie);

        when(showRepository.save(any(Show.class))).thenReturn(show);

        Show savedShow = showService.saveShow(show);

        assertNotNull(savedShow);
        assertEquals("SH123", savedShow.getShowId());
        assertEquals(50, savedShow.getSeatsRemainingGold());
    }

    @Test
    void testGetShowById() {
        Show show = new Show();
        show.setShowId("SH123");

        when(showRepository.findById("SH123")).thenReturn(Optional.of(show));

        Show foundShow = showService.getShowById("SH123");

        assertNotNull(foundShow);
        assertEquals("SH123", foundShow.getShowId());
    }

    @Test
    void testDeleteShow() {
        doNothing().when(showRepository).deleteById("SH123");

        showService.deleteShow("SH123");

        verify(showRepository, times(1)).deleteById("SH123");
    }
}
