package by.academy.it.reunova.implementations;

import by.academy.it.reunova.dto.LotDto;
import by.academy.it.reunova.dto.converter.interfaces.LotConverter;
import by.academy.it.reunova.entity.Buyer;
import by.academy.it.reunova.entity.Lot;
import by.academy.it.reunova.entity.OrderHistory;
import by.academy.it.reunova.interfaces.BasketService;
import by.academy.it.reunova.interfaces.LotService;
import by.academy.it.reunova.interfaces.OrderHistoryService;
import by.academy.it.reunova.price.PriceCalculator;
import by.academy.it.reunova.repository.BuyerRepository;
import by.academy.it.reunova.repository.LotRepository;
import by.academy.it.reunova.repository.OrderHistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderHistoryServiceImpl implements OrderHistoryService {

    private final LotRepository lotRepository;
    private final BuyerRepository buyerRepository;
    private final OrderHistoryRepository orderHistoryRepository;

    private final LotService lotService;
    private final BasketService basketService;

    private final LotConverter lotConverter;
    private final PriceCalculator priceCalculator;


    @Override
    public void buyLots(Integer buyerId) {
        for (Integer lotIdBuy : lotService.getLotListIdOfBuyer(buyerId)) {
            buyOneLot(buyerId, lotIdBuy);
        }
        for (Integer lotIdDeleteFromBasket : lotService.getLotListIdOfBuyer(buyerId)) {
            basketService.deleteLotFromBasket(buyerId, lotIdDeleteFromBasket);
        }
    }

    @Override
    public void buyOneLot(Integer buyerId, Integer lotId) {
        Buyer buyer = buyerRepository.findById(buyerId).orElse(null);
        Lot lot = lotRepository.findById(lotId).orElse(null);

        OrderHistory orderHistory = OrderHistory.builder()
                .buyer(buyer)
                .currentPriceOrderHistory(priceCalculator.getCurrentPrice(lotId))
                .dateOfPurchaseOrderHistory(LocalDate.now())
                .build();

        orderHistory.setLotInOrderHistory(lot);
        lot.setOrderHistory(orderHistory);
        orderHistoryRepository.save(orderHistory);
    }

    @Override
    public List<LotDto> findAllLotDtoInOrderHistory() {
        List<Lot> lotList = lotRepository.findAll();
        List<OrderHistory> orderHistoryList = orderHistoryRepository.findAll();
        List<Lot> checkLotList = new ArrayList<>();
        for (Lot lot : lotList) {
            for (OrderHistory history : orderHistoryList) {
                if (lot.getId().equals(history.getId())) {
                    checkLotList.add(lot);
                }
            }
        }
        return checkLotList.stream()
                .map(lotConverter::toLotDto)
                .collect(Collectors.toList());
    }
}
