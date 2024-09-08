package org.alpagu.sinemaotomasyonu.Business.Concretes;

import org.alpagu.sinemaotomasyonu.Business.Abstracts.BookingService;
import org.alpagu.sinemaotomasyonu.DataAccess.Abstracts.BookingRepository;
import org.alpagu.sinemaotomasyonu.Entities.Concretes.Booking;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookingServiceImpl implements BookingService {

    private final BookingRepository bookingRepository;

    @Autowired
    public BookingServiceImpl(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    @Override
    public Booking saveBooking(Booking booking) {
        return bookingRepository.save(booking);
    }

    @Override
    public Booking updateBooking(String bookingId, Booking booking) {
        Booking existingBooking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new RuntimeException("Booking not found"));
        existingBooking.setNoOfTickets(booking.getNoOfTickets());
        existingBooking.setTotalCost(booking.getTotalCost());
        existingBooking.setCardNumber(booking.getCardNumber());
        existingBooking.setNameOnCard(booking.getNameOnCard());
        existingBooking.setUser(booking.getUser());
        existingBooking.setShow(booking.getShow());
        return bookingRepository.save(existingBooking);
    }

    @Override
    public Booking getBookingById(String bookingId) {
        return bookingRepository.findById(bookingId)
                .orElseThrow(() -> new RuntimeException("Booking not found"));
    }

    @Override
    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }

    @Override
    public void deleteBooking(String bookingId) {
        bookingRepository.deleteById(bookingId);
    }
}

