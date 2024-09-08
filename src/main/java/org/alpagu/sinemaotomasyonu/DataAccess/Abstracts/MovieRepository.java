package org.alpagu.sinemaotomasyonu.DataAccess.Abstracts;


import org.alpagu.sinemaotomasyonu.Entities.Concretes.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<Movie, String> {

}
