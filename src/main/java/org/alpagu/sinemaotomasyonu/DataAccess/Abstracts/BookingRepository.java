package org.alpagu.sinemaotomasyonu.DataAccess.Abstracts;

import org.alpagu.sinemaotomasyonu.Entities.Concretes.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepository extends JpaRepository<Booking, String> {
}
