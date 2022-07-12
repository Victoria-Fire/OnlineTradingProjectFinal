package by.academy.it.reunova.controllers;

import by.academy.it.reunova.dto.BuyerDto;
import by.academy.it.reunova.interfaces.BuyerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class BuyerController {

    private final BuyerService buyerService;

    @GetMapping("/buyer")
    public String buyer(Model model) {
        List<BuyerDto> buyerDtoList = buyerService.findAllBuyerDto();
        model.addAttribute("buyerDtoList", buyerDtoList);
        return "buyer";
    }

    @GetMapping("/buyer/add")
    public String buyerAdd(@ModelAttribute("buyer") BuyerDto buyerDto) {
        return "buyer-add";
    }

    @PostMapping("/buyer/add")
    public String buyerPostAdd(BuyerDto buyerDto) {
        buyerService.saveBuyer(buyerDto);
        return "redirect:/buyer";
    }

    @GetMapping("/buyer/{id}/edit")
    public String buyerEdit(@PathVariable(value = "id") int id, Model model) {
        model.addAttribute("buyer", buyerService.findBuyerById(id));
        return "buyer-edit";
    }

    @PostMapping("/buyer/{id}/edit")
    public String buyerPostEdit(BuyerDto buyerDto) {
        buyerService.saveBuyer(buyerDto);
        return "redirect:/buyer";
    }

    @GetMapping("/buyer/{id}/delete")
    public String buyerDelete(@PathVariable(value = "id") int id) {
        buyerService.deleteBuyer(id);
        return "redirect:/buyer";
    }
}
