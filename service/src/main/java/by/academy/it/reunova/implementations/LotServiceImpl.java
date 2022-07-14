package by.academy.it.reunova.implementations;

import by.academy.it.reunova.dto.LotDto;
import by.academy.it.reunova.dto.converter.interfaces.LotConverter;
import by.academy.it.reunova.entity.Lot;
import by.academy.it.reunova.entity.OrderHistory;
import by.academy.it.reunova.interfaces.BasketService;
import by.academy.it.reunova.interfaces.LotService;
import by.academy.it.reunova.repository.LotRepository;
import by.academy.it.reunova.repository.OrderHistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class LotServiceImpl implements LotService {

    private final LotRepository lotRepository;
    private final OrderHistoryRepository orderHistoryRepository;

    private final LotConverter lotConverter;
    private final BasketService basketService;

    @Override
    public void saveLot(LotDto lotDto) {
        if (!((!LocalDate.now().isBefore(lotDto.getStartDateLot())) && (LocalDate.now().isBefore(lotDto.getEndDateLot().plusDays(1))))) {
            lotDto.setStatusLot(false);
        }
        lotRepository.save(lotConverter.toLot(lotDto));
    }

    @Override
    public void deleteLot(Integer lotId) {
        Lot lot = lotRepository.findById(lotId).orElse(null);
        lotRepository.delete(lot);
    }

    @Override
    public void deleteLotAnyway(Integer lotId) {

    }

    @Override
    public LotDto findLotById(Integer lotId) {
        Lot lot = lotRepository.findById(lotId).orElse(null);
        return lotConverter.toLotDto(lot);
    }

    @Override
    public List<LotDto> findAllLotDto() {
        return StreamSupport.stream(lotRepository.findAll().spliterator(), false)
                .map(lotConverter::toLotDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<LotDto> findAllLotDtoWithoutOrderHistory() {
        List<Lot> lotList = lotRepository.findAll();
        List<OrderHistory> orderHistoryList = orderHistoryRepository.findAll();

        for (int i = 0; i < lotList.size(); i++) {
            for (int j = 0; j < orderHistoryList.size(); j++) {
                if (lotList.get(i).getId().equals(orderHistoryList.get(j).getId())) {
                    lotList.remove(lotList.get(i));
                }
            }
        }
        return lotList.stream()
                .map(lotConverter::toLotDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<LotDto> findLotDtoForSale() {
        return findAllLotDto().stream()
                .filter(lotDto -> lotDto.getStatusLot().equals(true))
                .collect(Collectors.toList());
    }

    @Override
    public List<LotDto> getLotOfBuyer(Integer buyerId) {
        return lotRepository.getLotOfBuyerDao(buyerId).stream()
                .map(lotConverter::toLotDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<Integer> getLotListIdOfBuyer(Integer buyerId) {
        return lotRepository.getLotOfBuyerDao(buyerId).stream()
                .map(Lot -> Lot.getId())
                .collect(Collectors.toList());
    }

    @Override
    public List<LotDto> getLotOfBuyerStatusTrue(Integer buyerId) {
        return getLotOfBuyer(buyerId).stream()
                .filter(lotDto -> lotDto.getStatusLot().equals(true))
                .collect(Collectors.toList());
    }

    @Override
    public List<LotDto> getLotOfBuyerStatusFalse(Integer buyerId) {
        return getLotOfBuyer(buyerId).stream()
                .filter(lotDto -> lotDto.getStatusLot().equals(false))
                .collect(Collectors.toList());
    }

    @Override
    public void getLotOfBuyerWithoutSoldLot(Integer buyerId) {
        for (LotDto lots : getLotOfBuyer(buyerId)) {
            if (lots.getStatusLot().equals(false)) {
                basketService.deleteLotFromBasket(buyerId, lots.getId());
            }
        }
    }

    @Override
    public BigDecimal summationOfBuyerLotPricesInOrderConfirmation(Integer buyerId) {
        BigDecimal sumPrice = BigDecimal.valueOf(0);
        for (LotDto lot : getLotOfBuyerStatusTrue(buyerId)) {
            sumPrice = sumPrice.add(lot.getPriceLot());
        }
        return sumPrice;
    }

    @Override
    public BigDecimal summationOfBuyerLotPricesInOrder(Integer buyerId) {
        BigDecimal sumPrice = BigDecimal.valueOf(0);
        for (LotDto lot : getLotOfBuyerStatusFalse(buyerId)) {
            sumPrice = sumPrice.add(lot.getPriceLot());
        }
        return sumPrice;
    }

    @Override
    public List<LotDto> getLotOfBuyerForPurchase(Integer buyerId) {
        getLotOfBuyerWithoutSoldLot(buyerId);
        List<Lot> listLot = lotRepository.getLotOfBuyerStatusTrueForPurchaseDao(buyerId);
        for(Lot lot: listLot) {
            lot.setStatusLot(false);
            lotRepository.save(lot);
        }
        return listLot.stream()
                .map(lotConverter::toLotDto)
                .collect(Collectors.toList());
    }

    @Override
    public void returnStatusTrueFromPurchase(Integer buyerId) {
        List<Lot> listLot = lotRepository.getLotOfBuyerStatusFalseForPurchaseDao(buyerId);
        for (Lot lot : listLot) {
            lot.setStatusLot(true);
            lotRepository.save(lot);
        }
    }
}
