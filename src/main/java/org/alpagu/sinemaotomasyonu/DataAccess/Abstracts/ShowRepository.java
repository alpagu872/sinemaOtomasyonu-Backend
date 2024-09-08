package org.alpagu.sinemaotomasyonu.DataAccess.Abstracts;


import org.alpagu.sinemaotomasyonu.Entities.Concretes.Show;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ShowRepository extends JpaRepository<Show, String> {
    // Additional query methods can be defined here if needed

    @Query("SELECT s FROM Show s WHERE s.movie.movieId = :movieId")
    List<Show> findByMovie_MovieId(@Param("movieId") String movieId);

}
