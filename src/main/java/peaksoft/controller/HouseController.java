package peaksoft.controller;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import peaksoft.model.Customer;
import peaksoft.model.House;
import peaksoft.service.HouseService;
@Controller
@RequestMapping("/houseMain")
@RequiredArgsConstructor
public class HouseController {
    private final HouseService houseService;
    @GetMapping
        public String getAllHouse(Model model) {
            model.addAttribute("getAllHouses",houseService.getAllHouse());
            return "house/houseMain";
    }
    @GetMapping("/create")
    public String createHouse(Model model){
        model.addAttribute("newHouse",new House());
        return "house/createHouse";
    }
    @PostMapping("/save")
    public String saveHouse(Long agencyId,@ModelAttribute(name = "newHouse") House house){
        houseService.saveHouse(house,agencyId);
        return "redirect:/houseMain";
    }
    @GetMapping("/{agencyId}/getAgency")
    public String getHouse(@PathVariable("agencyId")Long id,Model model) {
        model.addAttribute("getMyHousesAgency", houseService.getAllHouseByAgencyId(id));
        return "house/houseAgency";
    }
    @DeleteMapping("/{agencyId}/deleteAgency")
    public String deleteAllHouse(@PathVariable("agencyId")Long id) {
        houseService.getAllHouseByAgencyId(id);
        return "redirect:/agencyMain";
    }

    @GetMapping("/{houseId}/get")
    public String getHouseById(@PathVariable("houseId")Long id,Model model) {
        model.addAttribute("getHouse",houseService.getHouseById(id));
        return "house/getPageHouse";
    }
    @GetMapping("/update/{houseId}")
    public String updatePage(@PathVariable("houseId") Long houseId, Model model) {
        model.addAttribute("updateHouse", houseService.getHouseById(houseId));
        return "house/updateHouse";
    }
    @PatchMapping("/replace/{houseId}")
    public String updateHouse(@ModelAttribute House house, @PathVariable Long houseId) {
        houseService.updateHouse(houseId,house);
        return "redirect:/houseMain";
    }
    @DeleteMapping("/{houseId}/delete")
    public String deleteHouse(@PathVariable("houseId")Long houseId){
        houseService.deleteHouse(houseId);
        return "redirect:/houseMain";
    }
    @GetMapping("/search")
    public String searchById(@RequestParam(name = "id") Long id, Model model) {
        House house = houseService.getHouseById(id);
        if (house == null) {
            return "redirect:/customerMain";
        }
        model.addAttribute("houseSearch", house);
        return "house/houseSearch";
    }
}
