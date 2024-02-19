package peaksoft.repo.impl;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import peaksoft.model.Booking;
import peaksoft.model.Customer;
import peaksoft.model.House;
import peaksoft.repo.BookingRepository;

import java.util.ArrayList;
import java.util.List;

@Transactional
@Repository
public class BookingRepositoryImpl implements BookingRepository {
    @Autowired
    private EntityManager entityManager;

    @Transactional
    @Override
    public void saveBooking(Booking booking) {
        entityManager.persist(booking);
    }


    @Override
    public void updateBooking(Long id, Booking booking) {
        try {
            Booking booking1 = entityManager.find(Booking.class, id);
            if (booking1 != null) {
                booking1.setCustomer(booking.getCustomer());
                booking1.setHouse(booking.getHouse());
            } else {
                System.out.println("Бронирование не найдено");
            }
        } catch (Exception e) {
            // Обработка исключения
            System.out.println("Ошибка при обновлении бронирования: " + e.getMessage());
        }
    }

    @Override
    public void deleteBooking(Long id) {
        try {
            Booking booking = entityManager.find(Booking.class, id);
            if (booking != null) {
                entityManager.remove(booking);
            } else {
                System.out.println("Бронирование не найдено");
            }
        } catch (Exception e) {
            // Обработка исключения
            System.out.println("Ошибка при удалении бронирования: " + e.getMessage());
        }
    }

    @Override
    public Booking getBookingById(Long id) {
        try {
            return entityManager.find(Booking.class, id);
        } catch (Exception e) {
            // Обработка исключения
            System.out.println("Ошибка при получении бронирования: " + e.getMessage());
            return null;
        }
    }

    @Override
    public List<Booking> getAllBooking() {
        try {
            return entityManager.createQuery("select l from Booking l ", Booking.class).getResultList();
        } catch (Exception e) {
            // Обработка исключения
            System.out.println("Ошибка при получении всех бронирований: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    @Override
    public void updateHouseBookingStatus(Long houseId) {
        try {
            entityManager.createQuery("UPDATE House h SET h.isBooked= true WHERE h.id = :houseId")
                    .setParameter("houseId", houseId)
                    .executeUpdate();
        } catch (Exception e) {
            // Обработка исключения
            System.out.println("Ошибка при обновлении статуса бронирования в доме: " + e.getMessage());
        }
    }
}