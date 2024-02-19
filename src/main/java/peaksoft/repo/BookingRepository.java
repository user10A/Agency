package peaksoft.repo;

import peaksoft.model.Booking;
import peaksoft.model.Customer;

import java.util.List;

public interface BookingRepository {
    //V  - Booking деген баракча болсун.
    // Ал баракчада бардык booked болгон уйлордун тиби жана адреси жана ал уйду ким бронировать эткен адамдын толук аты чыксын.
    // Жана ал баракчада <"book" же "забронировать"> деген баскыч болсун.
    // Ал баскычты басканда жаны баракча ачылып customer уйду book кылыш керек.
    void saveBooking(Booking booking);
    void updateBooking(Long id,Booking booking);
    void deleteBooking(Long id);
    Booking getBookingById(Long id);
    List<Booking>getAllBooking();
    void updateHouseBookingStatus(Long houseId);

}
