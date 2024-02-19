package peaksoft.controller;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import peaksoft.model.Agency;
import peaksoft.service.AgencyService;
import peaksoft.service.CustomerService;

import java.util.List;

@Controller
@RequestMapping("/agencyMain")
@RequiredArgsConstructor
public class AgencyController {
    private final AgencyService agencyService;
    private final CustomerService customerService;

    @GetMapping
    public String getAllLessons(Model model) {
        model.addAttribute("getAllAgencies",agencyService.getAllAgencies());
        return "agency/agencyMain";
    }
    @GetMapping("/create")
    public String createAgency(Model model){
        model.addAttribute("newAgency",new Agency());
        return "agency/createAgency";
    }
    @PostMapping("/save")
    public String saveLesson(@ModelAttribute(name = "newAgency") Agency agency){
        agencyService.saveAgency(agency);
        return "redirect:/agencyMain";
    }
    @DeleteMapping("/{agencyId}/delete")
    public String deleteAgency(@PathVariable("agencyId")Long id){
        agencyService.deleteAgency(id);
        return "redirect:/agencyMain";
    }
    @GetMapping("/{agencyId}/get")
    public String getAgency(@PathVariable("agencyId")Long id,Model model){
        model.addAttribute("getAgency",agencyService.getAgencyById(id));
        return "agency/getPageAgency";
    }
    @GetMapping("/update/{agencyId}")
    public String updatePage(@PathVariable("agencyId") Long agencyId, Model model) {
        model.addAttribute("updateAgency", agencyService.getAgencyById(agencyId));
        return "agency/updatePageAgency";
    }

    @PatchMapping("/replace/{agencyId}")
    public String update(@ModelAttribute Agency agency, @PathVariable Long agencyId) {
        agencyService.updateAgency(agencyId, agency);
        return "redirect:/agencyMain";
    }
    @GetMapping("/{id}/profile/{cId}")
    public String getSalonProfile(@PathVariable("id") long id, Model model,@PathVariable("cId") long cId){
        model.addAttribute("agencyFound",agencyService.getAgencyById(id));
        model.addAttribute("customer", customerService.getCustomerById(cId));
        return "agency/salon_profile";
    }
    @GetMapping("/{id}/notAssignedEmployees")
    public String getNotAssignedCustomer(@PathVariable("id") Long id, Model model){
        model.addAttribute("agencyFound", agencyService.getAgencyById(id));
        model.addAttribute("notAssignedEmployees", agencyService.getNotAssignedCustomerTOAgency(id));
        return "agency/not_assigned_employees";
    }

    @PostMapping("/{id}/assignEmployees")
    public String assignEmployees(@PathVariable("id") long id, @RequestParam List<Long> employeeId){
        agencyService.assignCustomerToAgency(id,employeeId);
        return "redirect:customerMain/customerAgency";
    }

    @GetMapping("/search")
    public String searchById(@RequestParam(name = "id") Long id, Model model) {
        Agency agency = agencyService.getAgencyById(id);
        if (agency == null) {
            return "redirect:/agencyMain";
        }
        model.addAttribute("agencySearch", agency);
        return "agency/agencySearch";
    }

}
