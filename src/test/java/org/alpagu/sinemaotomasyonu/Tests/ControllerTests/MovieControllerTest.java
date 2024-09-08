package org.alpagu.sinemaotomasyonu.Tests.ControllerTests;

import org.alpagu.sinemaotomasyonu.Business.Abstracts.MovieService;
import org.alpagu.sinemaotomasyonu.Api.Controllers.MovieController;
import org.alpagu.sinemaotomasyonu.Entities.Concretes.Movie;
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

@WebMvcTest(MovieController.class)
class MovieControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MovieService movieService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateMovie() throws Exception {
        Movie movie = new Movie();
        movie.setMovieId("M123");
        movie.setName("Inception");

        when(movieService.saveMovie(any(Movie.class))).thenReturn(movie);

        mockMvc.perform(post("/api/movies")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"movieId\":\"M123\",\"name\":\"Inception\"}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.movieId").value("M123"))
                .andExpect(jsonPath("$.name").value("Inception"));
    }

    @Test
    void testGetMovieById() throws Exception {
        Movie movie = new Movie();
        movie.setMovieId("M123");
        movie.setName("Inception");

        when(movieService.getMovieById("M123")).thenReturn(movie);

        mockMvc.perform(get("/api/movies/M123")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.movieId").value("M123"))
                .andExpect(jsonPath("$.name").value("Inception"));
    }

    @Test
    void testGetAllMovies() throws Exception {
        Movie movie1 = new Movie();
        movie1.setMovieId("M123");
        movie1.setName("Inception");

        Movie movie2 = new Movie();
        movie2.setMovieId("M124");
        movie2.setName("Interstellar");

        when(movieService.getAllMovies()).thenReturn(Arrays.asList(movie1, movie2));

        mockMvc.perform(get("/api/movies")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].movieId").value("M123"))
                .andExpect(jsonPath("$[1].movieId").value("M124"));
    }

    @Test
    void testDeleteMovie() throws Exception {
        doNothing().when(movieService).deleteMovie("M123");

        mockMvc.perform(delete("/api/movies/M123")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }
}
