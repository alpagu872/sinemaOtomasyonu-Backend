package org.alpagu.sinemaotomasyonu.Business.Abstracts;


import org.alpagu.sinemaotomasyonu.Entities.Concretes.Ticket;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TicketService {
    Page<Ticket> getAllTickets(Pageable pageable);

    Ticket saveTicket(Ticket ticket);
    Ticket updateTicket(String ticketId, Ticket ticket);
    Ticket getTicketById(String ticketId);
    List<Ticket> getAllTickets();
    void deleteTicket(String ticketId);
}
