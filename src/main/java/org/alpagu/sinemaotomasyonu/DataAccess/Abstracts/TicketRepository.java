package org.alpagu.sinemaotomasyonu.DataAccess.Abstracts;

import org.alpagu.sinemaotomasyonu.Entities.Concretes.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepository extends JpaRepository<Ticket, String> {
    // Additional query methods can be defined here if needed
}
