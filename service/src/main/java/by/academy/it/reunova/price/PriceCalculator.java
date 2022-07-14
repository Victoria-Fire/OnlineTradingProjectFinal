package by.academy.it.reunova.price;

import by.academy.it.reunova.entity.Lot;
import by.academy.it.reunova.repository.LotRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class PriceCalculator {

    private final LotRepository lotRepository;
    private static BigDecimal currentPrice = null;

    public BigDecimal getCurrentPrice(Integer lotId) {
        BigDecimal priceDifference = countingDiffOfPrice(lotId);
        BigDecimal differenceOfDays = BigDecimal.valueOf(countingDiffOfDays(lotId));

        Lot lot = lotRepository.findById(lotId).orElseThrow();

        if (!((!LocalDate.now().isBefore(lot.getStartDateLot())) && (LocalDate.now().isBefore(lot.getEndDateLot().plusDays(1))))) {
            lot.setStatusLot(false);
            lotRepository.save(lot);
            return BigDecimal.valueOf(00.00);
        }

        Map<LocalDate, BigDecimal> map = new HashMap<>();

        BigDecimal currentPriceValue = lot.getStartPriceLot();
        for (int i = 0; i <= countingDiffOfDays(lotId); i++) {
            map.put(lot.getStartDateLot().plusDays(i), currentPriceValue);
            currentPriceValue = (currentPriceValue.subtract(priceDifference.divide(differenceOfDays, 2, RoundingMode.HALF_UP)));
        }

        for (Map.Entry<LocalDate, BigDecimal> pair : map.entrySet()) {
            if (pair.getKey().equals(LocalDate.now())) {
                currentPrice = pair.getValue();
            }
        }
        return currentPrice;
    }

        private Integer countingDiffOfDays(Integer lotId) {
        Lot lot = lotRepository.findById(lotId).orElseThrow();
        Integer diffDays = (lot.getEndDateLot().getDayOfYear() - lot.getStartDateLot().getDayOfYear());
        return diffDays;
    }

    private BigDecimal countingDiffOfPrice(Integer lotId) {
        Lot lot = lotRepository.findById(lotId).orElseThrow();
        BigDecimal diffPrice = lot.getStartPriceLot().subtract(lot.getEndPriceLot());
        return diffPrice;
    }

}
