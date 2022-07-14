package by.academy.it.reunova.controllers;

import by.academy.it.reunova.dto.LotDto;
import by.academy.it.reunova.interfaces.LotService;
import by.academy.it.reunova.interfaces.OrderHistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class AdminController {

    private final LotService lotService;
    private final OrderHistoryService orderHistoryService;

    @GetMapping("/admin")
    public String admin() {
        return "admin";
    }

    @GetMapping("/admin/listLot")
    public String adminListLot(Model model) {
        model.addAttribute("lotList", lotService.findAllLotDtoWithoutOrderHistory());
        return "admin-listLot";
    }

    @GetMapping("/admin/lotAdd")
    public String adminLotAdd(@ModelAttribute("lot") LotDto lotDto) {
        return "admin-lotAdd";
    }

    @PostMapping("/admin/lotAdd")
    public String adminPostLotAdd(LotDto lotDto) {
        lotService.saveLot(lotDto);
        return "redirect:/admin/listLot";
    }

    @GetMapping("/admin/{id}/lotEdit")
    public String adminLotEdit(@PathVariable(value = "id") int id, Model model) {
        model.addAttribute("lot", lotService.findLotById(id));
        return "admin-lotEdit";
    }

    @PostMapping("/admin/{id}/lotEdit")
    public String adminPostLotEdit(LotDto lotDto) {
        lotService.saveLot(lotDto);
        return "redirect:/admin/listLot";
    }

    @GetMapping("/admin/{id}/lotDelete")
    public String adminLotDelete(@PathVariable(value = "id") int id, Model model) {
        model.addAttribute("lot", lotService.findLotById(id));
        return "admin-lotDelete";
    }

    @PostMapping("/admin/{id}/lotDelete")
    public String adminPostLotDelete(@PathVariable(value = "id") int id) {
        lotService.deleteLot(id);
        return "redirect:/admin/listLot";
    }

    @GetMapping("/admin/lotInOrderHistory")
    public String adminLotInOrderHistory(Model model) {
        model.addAttribute("lotListInOrderHistory", orderHistoryService.findAllLotDtoInOrderHistory());
        return "admin-lotInOrderHistory";
    }
}
