package org.alpagu.sinemaotomasyonu.Tests.ServiceTests;

import org.alpagu.sinemaotomasyonu.Business.Concretes.TicketServiceImpl;
import org.alpagu.sinemaotomasyonu.DataAccess.Abstracts.TicketRepository;
import org.alpagu.sinemaotomasyonu.Entities.Concretes.Booking;
import org.alpagu.sinemaotomasyonu.Entities.Concretes.Ticket;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class TicketServiceImplTest {

    @InjectMocks
    private TicketServiceImpl ticketService;

    @Mock
    private TicketRepository ticketRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSaveTicket() {
        Ticket ticket = new Ticket();
        ticket.setTicketId("T123");
        ticket.setTicketClass("GLD");
        ticket.setPrice(200);

        Booking booking = new Booking();
        booking.setBookingId("B123");
        ticket.setBooking(booking);

        when(ticketRepository.save(any(Ticket.class))).thenReturn(ticket);

        Ticket savedTicket = ticketService.saveTicket(ticket);

        assertNotNull(savedTicket);
        assertEquals("T123", savedTicket.getTicketId());
        assertEquals("GLD", savedTicket.getTicketClass());
    }

    @Test
    void testGetTicketById() {
        Ticket ticket = new Ticket();
        ticket.setTicketId("T123");

        when(ticketRepository.findById("T123")).thenReturn(Optional.of(ticket));

        Ticket foundTicket = ticketService.getTicketById("T123");

        assertNotNull(foundTicket);
        assertEquals("T123", foundTicket.getTicketId());
    }

    @Test
    void testDeleteTicket() {
        doNothing().when(ticketRepository).deleteById("T123");

        ticketService.deleteTicket("T123");

        verify(ticketRepository, times(1)).deleteById("T123");
    }
}
