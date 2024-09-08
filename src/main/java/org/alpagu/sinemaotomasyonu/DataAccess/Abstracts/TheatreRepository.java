package org.alpagu.sinemaotomasyonu.DataAccess.Abstracts;


import org.alpagu.sinemaotomasyonu.Entities.Concretes.Theatre;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TheatreRepository extends JpaRepository<Theatre, String> {
    // Additional query methods can be defined here if needed
}
