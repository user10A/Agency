package peaksoft.controller;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import peaksoft.model.Agency;
import peaksoft.model.Booking;
import peaksoft.model.Customer;
import peaksoft.service.BookingService;
import peaksoft.service.CustomerService;
import peaksoft.service.HouseService;

@Controller
@RequestMapping("/customerMain")
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerService customerService;
    private final HouseService houseService;
    private final BookingService bookingService;

    @GetMapping
    public String getAllCustomers(Model model) {
        model.addAttribute("getAllCustomers",customerService.getAllCustomers());
        return "customer/customerMain";
    }
    @GetMapping("/create")
    public String createCustomer(Model model){
        model.addAttribute("newCustomer",new Customer());
        return "customer/createCustomer";
    }
    @PostMapping("/save")
    public String saveCustomer(@ModelAttribute(name = "newCustomer") Customer customer){
        customerService.saveCustomer(customer);
        return "redirect:/customerMain";
    }
    @GetMapping("/{customerId}/get")
    public String getCustomerById(@PathVariable("customerId")Long customerId,Model model) {
        model.addAttribute("getCustomer",customerService.getCustomerById(customerId));
        return "customer/getPageCustomer";
    }
    @DeleteMapping("/{customerId}/delete")
    public String deleteCustomer(@PathVariable("customerId") Long customerId){
        customerService.deleteCustomer(customerId);
        return "redirect:/customerMain";
    }
    @GetMapping("/update/{customerId}")
    public String getUpdate(@PathVariable("customerId") Long customerId , Model model){
        model.addAttribute("updateCustomer",customerService.getCustomerById(customerId));
        return "customer/customerUpdate";
    }
    @PatchMapping("/replace/{customerId}")
    public String customerUpdate(@PathVariable("customerId") Long Id,@ModelAttribute Customer customer){
        customerService.updateCustomer(Id,customer);
        return "redirect:/customerMain";
    }
    //booking
    @PostMapping("/{cId}/createBooking/{emId}")
    public String createBooking( @PathVariable("cId") long cId, @PathVariable("emId") long emId){
        Booking booking = new Booking();
        booking.setCustomer(customerService.getCustomerById(cId));
        booking.setHouse(houseService.getHouseById(emId));
        Customer customer = customerService.getCustomerById(cId);

        bookingService.saveBooking(booking);
        customer.getBookings().add(booking);
        customerService.updateCustomer(customer.getId(),customer);
        return "redirect:/customer/" + customer.getId() + "/profile";
    }

    // agency customer ALL
    @GetMapping("/{agencyId}/getAgencyCustomers")
    public String getHouse(@PathVariable("agencyId")Long id,Model model) {
        model.addAttribute("getMyCustomerAgency",customerService.getAllCustomersByAgencyId(id));
        return "customer/customerAgency";
    }
    @GetMapping("/search")
    public String searchById(@RequestParam(name = "id") Long id, Model model) {
        Customer customer = customerService.getCustomerById(id);
        if (customer == null) {
            return "redirect:/customerMain";
        }
        model.addAttribute("customerSearch", customer);
        return "customer/customerSearch";
    }
}
