package by.academy.it.reunova.controllers;

import by.academy.it.reunova.dto.BuyerDto;
import by.academy.it.reunova.interfaces.BasketService;
import by.academy.it.reunova.interfaces.BuyerService;
import by.academy.it.reunova.interfaces.LotService;
import by.academy.it.reunova.interfaces.OrderHistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class BuyerController {

    private final BuyerService buyerService;
    private final LotService lotService;
    private final BasketService basketService;
    private final OrderHistoryService orderHistoryService;

    @GetMapping("/buyer")
    public String buyer(Model model) {
        model.addAttribute("buyers", buyerService.findAllBuyerDto());
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

    @GetMapping("/buyer/{id}/catalog")
    public String buyerCatalog(@PathVariable(value = "id") int id, Model model) {
        model.addAttribute("lots", lotService.findLotDtoForSale());
        model.addAttribute("lotListIdOfBuyer", lotService.getLotListIdOfBuyer(id));
        return "buyer-catalog";
    }

    @PostMapping("/buyer/{id}/catalog")
    public String buyerPostCatalog(@PathVariable(value = "id") int buyerId,
                                   @RequestParam String checkBasketCatalog,
                                   @RequestParam("lotId") int lotId,
                                   Model model) {
        switch (checkBasketCatalog) {
            case ("addToBasketCatalog"):
                basketService.addLotToBasket(buyerId, lotId);
                break;
            case ("deleteFromBasketCatalog"):
                basketService.deleteLotFromBasket(buyerId, lotId);
                break;
        }
        return buyerCatalog(buyerId, model);
    }

    @GetMapping("/buyer/{id}/basket")
    public String buyerBasket(@PathVariable(value = "id") int id, Model model) {
        model.addAttribute("getLotOfBuyer", lotService.getLotOfBuyer(id));
        return "buyer-basket";
    }

    @PostMapping("/buyer/{id}/basket")
    public String buyerPostBasket(@PathVariable(value = "id") int buyerId,
                                  @RequestParam String checkBasket,
                                  @RequestParam("lotId") int lotId,
                                  Model model) {
        switch (checkBasket) {
            case ("deleteFromBasket"):
                basketService.deleteLotFromBasket(buyerId, lotId);
                break;
            case ("returnToBasketFromOrder"):
                lotService.getLotOfBuyerWithoutSoldLot(buyerId);
                break;
           case ("returnToBasketFromPurchase"):
                lotService.returnStatusTrueFromPurchase(buyerId);
                break;
        }
        return buyerBasket(buyerId, model);
    }

    @GetMapping("/buyer/{id}/orderConfirmation")
    public String buyerOrderConfirmation(@PathVariable(value = "id") int id, Model model) {
        model.addAttribute("sumPrice", lotService.summationOfBuyerLotPricesInOrderConfirmation(id));
        model.addAttribute("listLotStatusTrue", lotService.getLotOfBuyerStatusTrue(id));
        model.addAttribute("listLotStatusFalse", lotService.getLotOfBuyerStatusFalse(id));
        return "buyer-orderConfirmation";
    }

    @GetMapping("/buyer/{id}/order")
    public String buyerOrder(@PathVariable(value = "id") int id, Model model) {
        model.addAttribute("listLotForPurchase", lotService.getLotOfBuyerForPurchase(id));
        model.addAttribute("sumPrice", lotService.summationOfBuyerLotPricesInOrder(id));
        return "buyer-order";
    }

    @GetMapping("/buyer/{id}/orderIsPaid")
    public String buyerOrderIsPaid(@PathVariable(value = "id") int id) {
        orderHistoryService.buyLots(id);
        return "buyer-orderIsPaid";
    }
}
