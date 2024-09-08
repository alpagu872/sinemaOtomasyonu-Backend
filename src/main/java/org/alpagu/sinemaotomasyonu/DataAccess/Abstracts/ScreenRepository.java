package org.alpagu.sinemaotomasyonu.DataAccess.Abstracts;


import org.alpagu.sinemaotomasyonu.Entities.Concretes.Screen;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScreenRepository extends JpaRepository<Screen, String> {
    // Additional query methods can be defined here if needed
}
