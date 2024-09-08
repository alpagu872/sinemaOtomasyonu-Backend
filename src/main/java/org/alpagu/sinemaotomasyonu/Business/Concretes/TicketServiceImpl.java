package org.alpagu.sinemaotomasyonu.Business.Concretes;


import org.alpagu.sinemaotomasyonu.Business.Abstracts.TicketService;
import org.alpagu.sinemaotomasyonu.DataAccess.Abstracts.TicketRepository;
import org.alpagu.sinemaotomasyonu.Entities.Concretes.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketServiceImpl implements TicketService {

    private final TicketRepository ticketRepository;

    @Autowired
    public TicketServiceImpl(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    @Override
    public Page<Ticket> getAllTickets(Pageable pageable) {
        return ticketRepository.findAll(pageable);
    }

    @Override
    public Ticket saveTicket(Ticket ticket) {
        return ticketRepository.save(ticket);
    }

    @Override
    public Ticket updateTicket(String ticketId, Ticket ticket) {
        Ticket existingTicket = ticketRepository.findById(ticketId)
                .orElseThrow(() -> new RuntimeException("Ticket not found"));
        existingTicket.setBooking(ticket.getBooking());
        existingTicket.setTicketClass(ticket.getTicketClass());
        existingTicket.setPrice(ticket.getPrice());
        return ticketRepository.save(existingTicket);
    }

    @Override
    public Ticket getTicketById(String ticketId) {
        return ticketRepository.findById(ticketId)
                .orElseThrow(() -> new RuntimeException("Ticket not found"));
    }

    @Override
    public List<Ticket> getAllTickets() {
        return ticketRepository.findAll();
    }

    @Override
    public void deleteTicket(String ticketId) {
        ticketRepository.deleteById(ticketId);
    }
}
