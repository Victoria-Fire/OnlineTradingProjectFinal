package by.academy.it.reunova.controllers;

import by.academy.it.reunova.dto.BuyerDto;
import by.academy.it.reunova.dto.LotDto;
import by.academy.it.reunova.interfaces.BuyerService;
import by.academy.it.reunova.interfaces.LotService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class AdminController {

    private final LotService lotService;

    @GetMapping("/admin")
    public String admin() {
        return "admin";
    }

    @GetMapping("/adminListLot")
    public String adminListLot(Model model) {
        List<LotDto> lotDtoList = lotService.findAllLotDto();
        model.addAttribute("lotDtoList", lotDtoList);
        return "admin-listLot";
    }

    @GetMapping("/admin/lotAdd")
    public String adminLotAdd(@ModelAttribute("lot") LotDto lotDto) {
        return "admin-lotAdd";
    }

    @PostMapping("/admin/lotAdd")
    public String adminPostLotAdd(LotDto lotDto) {
        lotService.saveLot(lotDto);
        return "redirect:/adminListLot";
    }


}
