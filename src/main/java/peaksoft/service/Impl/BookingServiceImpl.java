package peaksoft.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import peaksoft.model.Booking;
import peaksoft.repo.BookingRepository;
import peaksoft.service.BookingService;

import java.util.List;

@Service
public class BookingServiceImpl implements BookingService {
    @Autowired
    BookingRepository bookingRepository;
    @Override
    public void saveBooking(Booking booking) {
    bookingRepository.saveBooking(booking);
    }

    @Override
    public void updateBooking(Long id, Booking booking) {
        bookingRepository.updateBooking(id,booking);
    }

    @Override
    public void deleteBooking(Long id) {
        bookingRepository.deleteBooking(id);
    }

    @Override
    public Booking getBookingById(Long id) {
        return bookingRepository.getBookingById(id);
    }

    @Override
    public List<Booking> getAllBooking() {
        return bookingRepository.getAllBooking();
    }

    @Override
    public void updateHouseBookingStatus(Long houseId) {
        bookingRepository.updateHouseBookingStatus(houseId);
    }
}
