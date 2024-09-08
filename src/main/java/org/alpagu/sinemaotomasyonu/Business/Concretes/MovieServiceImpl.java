package org.alpagu.sinemaotomasyonu.Business.Concretes;


import org.alpagu.sinemaotomasyonu.Business.Abstracts.MovieService;
import org.alpagu.sinemaotomasyonu.DataAccess.Abstracts.MovieRepository;
import org.alpagu.sinemaotomasyonu.Entities.Concretes.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieServiceImpl implements MovieService {

    private final MovieRepository movieRepository;

    @Autowired
    public MovieServiceImpl(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @Override
    public Movie saveMovie(Movie movie) {
        return movieRepository.save(movie);
    }

    @Override
    public Movie updateMovie(String movieId, Movie movie) {
        Movie existingMovie = movieRepository.findById(movieId)
                .orElseThrow(() -> new RuntimeException("Movie not found"));
        existingMovie.setName(movie.getName());
        existingMovie.setLanguage(movie.getLanguage());
        existingMovie.setGenre(movie.getGenre());
        existingMovie.setTargetAudience(movie.getTargetAudience());
        return movieRepository.save(existingMovie);
    }

    @Override
    public Movie getMovieById(String movieId) {
        return movieRepository.findById(movieId)
                .orElseThrow(() -> new RuntimeException("Movie not found"));
    }

    @Override
    public List<Movie> getAllMovies() {
        return movieRepository.findAll();
    }

    @Override
    public void deleteMovie(String movieId) {
        movieRepository.deleteById(movieId);
    }
}
