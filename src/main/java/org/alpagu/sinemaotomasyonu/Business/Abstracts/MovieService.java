package org.alpagu.sinemaotomasyonu.Business.Abstracts;

import org.alpagu.sinemaotomasyonu.Entities.Concretes.Movie;

import java.util.List;

public interface MovieService {
    Movie saveMovie(Movie movie);
    Movie updateMovie(String movieId, Movie movie);
    Movie getMovieById(String movieId);
    List<Movie> getAllMovies();
    void deleteMovie(String movieId);
}
