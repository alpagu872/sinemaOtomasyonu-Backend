package org.alpagu.sinemaotomasyonu.Tests.ServiceTests;

import org.alpagu.sinemaotomasyonu.Business.Concretes.BookingServiceImpl;
import org.alpagu.sinemaotomasyonu.DataAccess.Abstracts.BookingRepository;
import org.alpagu.sinemaotomasyonu.Entities.Concretes.Booking;
import org.alpagu.sinemaotomasyonu.Entities.Concretes.Show;
import org.alpagu.sinemaotomasyonu.Entities.Concretes.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class BookingServiceImplTest {

    @InjectMocks
    private BookingServiceImpl bookingService;

    @Mock
    private BookingRepository bookingRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSaveBooking() {
        Booking booking = new Booking();
        booking.setBookingId("B123");
        booking.setNoOfTickets(2);
        booking.setTotalCost(400);

        User user = new User();
        user.setWebUserId("U123");
        booking.setUser(user);

        Show show = new Show();
        show.setShowId("SH123");
        booking.setShow(show);

        when(bookingRepository.save(any(Booking.class))).thenReturn(booking);

        Booking savedBooking = bookingService.saveBooking(booking);

        assertNotNull(savedBooking);
        assertEquals("B123", savedBooking.getBookingId());
        assertEquals(2, savedBooking.getNoOfTickets());
    }

    @Test
    void testGetBookingById() {
        Booking booking = new Booking();
        booking.setBookingId("B123");

        when(bookingRepository.findById("B123")).thenReturn(Optional.of(booking));

        Booking foundBooking = bookingService.getBookingById("B123");

        assertNotNull(foundBooking);
        assertEquals("B123", foundBooking.getBookingId());
    }

    @Test
    void testDeleteBooking() {
        doNothing().when(bookingRepository).deleteById("B123");

        bookingService.deleteBooking("B123");

        verify(bookingRepository, times(1)).deleteById("B123");
    }
}
