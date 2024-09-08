package org.alpagu.sinemaotomasyonu.Tests.ServiceTests;

import org.alpagu.sinemaotomasyonu.Business.Concretes.MovieServiceImpl;
import org.alpagu.sinemaotomasyonu.DataAccess.Abstracts.MovieRepository;
import org.alpagu.sinemaotomasyonu.Entities.Concretes.Movie;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class MovieServiceImplTest {

    @InjectMocks
    private MovieServiceImpl movieService;

    @Mock
    private MovieRepository movieRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSaveMovie() {
        Movie movie = new Movie();
        movie.setMovieId("M123");
        movie.setName("Inception");

        when(movieRepository.save(any(Movie.class))).thenReturn(movie);

        Movie savedMovie = movieService.saveMovie(movie);

        assertNotNull(savedMovie);
        assertEquals("M123", savedMovie.getMovieId());
        assertEquals("Inception", savedMovie.getName());
    }

    @Test
    void testGetMovieById() {
        Movie movie = new Movie();
        movie.setMovieId("M123");

        when(movieRepository.findById("M123")).thenReturn(Optional.of(movie));

        Movie foundMovie = movieService.getMovieById("M123");

        assertNotNull(foundMovie);
        assertEquals("M123", foundMovie.getMovieId());
    }

    @Test
    void testDeleteMovie() {
        doNothing().when(movieRepository).deleteById("M123");

        movieService.deleteMovie("M123");

        verify(movieRepository, times(1)).deleteById("M123");
    }
}
