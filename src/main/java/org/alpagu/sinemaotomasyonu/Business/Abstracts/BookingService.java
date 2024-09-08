package org.alpagu.sinemaotomasyonu.Business.Abstracts;


import org.alpagu.sinemaotomasyonu.Entities.Concretes.Booking;

import java.util.List;

public interface BookingService {
    Booking saveBooking(Booking booking);
    Booking updateBooking(String bookingId, Booking booking);
    Booking getBookingById(String bookingId);
    List<Booking> getAllBookings();
    void deleteBooking(String bookingId);
}

