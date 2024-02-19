package peaksoft.controller;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import peaksoft.model.Agency;
import peaksoft.model.Booking;
import peaksoft.model.Customer;
import peaksoft.model.House;
import peaksoft.service.BookingService;
import peaksoft.service.CustomerService;
import peaksoft.service.HouseService;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;


@Controller
@RequestMapping("/bookingMain")
@RequiredArgsConstructor
public class BookingController {
    private final CustomerService customerService;
    private final HouseService houseService;
    private final BookingService bookingService;
    @GetMapping
    public String getAllBooking(Model model){
        List<Booking> allBookings = bookingService.getAllBooking();
        model.addAttribute("getAllBooking", allBookings);
        return "booking/bookingMain";
    }




//    @GetMapping("/booking/{bookingId}")
//    private String editBooking(Model model, @PathVariable("bookingId") Long bookingId) {
//        Booking booking = bookingService.getBookingById(bookingId);
//
//        if (booking == null) {
//            model.addAttribute("booking", booking);
//            return "/booking/editBooking";
//        } else {
//
//            return "redirect:/house/houseMain"; // Замените "errorPage" на имя вашей страницы с ошибкой
//        }
//    }


//    @PostMapping("/book/{houseId}")
//    public String bookHouse(@PathVariable Long houseId, Principal principal) {
//        // Получаем текущего пользователя (замените 3L на фактический идентификатор пользователя)
//        Customer customer = customerService.getCustomerById(4L);
//
//        // Получаем информацию о доме по его идентификатору
//        House house = houseService.getHouseById(houseId);
//
//        // Проверяем, не забронирован ли дом уже
//        if (!house.isBooked()) {
//            // Создаем новое бронирование
//            Booking booking = new Booking();
//            booking.setCustomer(customer);
//            booking.setHouse(house);
//            // Сохраняем бронирование в базе данных
//            bookingService.saveBooking(booking);
//
//            // Обновляем статус дома на "забронирован"
//            house.setBooked(true);
//            houseService.updateHouse(houseId,house);
//            // Перенаправляем на страницу успеха или домой
//            return "redirect:/bookingMain";
//        } else {
//            // Обрабатываем случай, когда дом уже забронирован
//            // Можно вывести сообщение об ошибке пользователю
//            return "redirect:/house/houseMain"; // Перенаправляем на страницу ошибки или домой
//        }
//    }

}
