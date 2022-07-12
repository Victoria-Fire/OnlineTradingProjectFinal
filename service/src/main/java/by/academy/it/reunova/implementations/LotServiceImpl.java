package by.academy.it.reunova.implementations;

import by.academy.it.reunova.dto.LotDto;
import by.academy.it.reunova.dto.converter.implementations.LotConverterImpl;
import by.academy.it.reunova.dto.converter.interfaces.LotConverter;
import by.academy.it.reunova.entity.Lot;
import by.academy.it.reunova.interfaces.LotService;
import by.academy.it.reunova.repository.LotRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class LotServiceImpl implements LotService {

    private final LotConverter lotConverter = new LotConverterImpl();
    private final LotRepository lotRepository;
    private static BigDecimal currentPrice = null;

    @Override
    public void saveLot(LotDto lotDto) {
        lotRepository.save(lotConverter.toLot(lotDto));
    }

    @Override
    public void deleteLot(Integer lotId) {

    }

    @Override
    public void deleteLotAnyway(Integer lotId) {

    }

    @Override
    public BigDecimal getCurrentPrice(Integer lotId, LocalDate currentDate) {
        BigDecimal priceDifference = countingDiffOfPrice(lotId);
        BigDecimal differenceOfDays = BigDecimal.valueOf(countingDiffOfDays(lotId));

        Lot lot = lotRepository.findById(lotId).orElseThrow();

        if (!((!currentDate.isBefore(lot.getStartDateLot())) && (currentDate.isBefore(lot.getEndDateLot().plusDays(1))))) {
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
            if (pair.getKey().equals(currentDate)) {
                currentPrice = pair.getValue();
            }
        }
        return currentPrice;
    }

    @Override
    public List<LotDto> findAllLotDto() {

        return StreamSupport
                .stream(lotRepository.findAll().spliterator(), false)
                .map(lotConverter::toLotDto)
                .collect(Collectors.toList());
    }

//    @Override
//    public List<BuyerDto> findAllBuyerDto() {
//        return StreamSupport
//                .stream(buyerRepository.findAll().spliterator(), false)
//                .map(buyerConverter::toBuyerDto)
//                .collect(Collectors.toList());
//    }

//    @Override
//    public List<LotDto> findAllLotDto(LocalDate currentDate) {
//        LotService lotService = new LotServiceImpl();
//        LotDao lotDao = DaoFactory.getInstance().getLotDao();
//        List<Lot> lotList = lotDao.findAll();
//
//        return lotList.stream()
//                .map(Lot -> LotDto.builder()
//                        .id(Lot.getId())
//                        .nameLot(Lot.getNameLot())
//                        .description(Lot.getDescription())
//                        .startDate(Lot.getStartDate())
//                        .endDate(Lot.getEndDate())
//                        .startPrice(Lot.getStartPrice())
//                        .endPrice(Lot.getEndPrice())
//                        .status(Lot.getStatus())
//                        .price(lotService.getCurrentPrice(Lot.getId(), currentDate))
//                        .build())
//                .collect(Collectors.toList());
//    }

    @Override
    public List<LotDto> findAllLotDtoWithoutOrderHistory(LocalDate currentDate) {
        return null;
    }

    @Override
    public List<LotDto> findLotDtoForSale(LocalDate currentDate) {
        return null;
    }

    @Override
    public List<LotDto> getLotOfBuyer(Integer buyerId, LocalDate currentDate) {
        return null;
    }

    @Override
    public List<Integer> getLotListIdOfBuyer(Integer buyerId) {
        return null;
    }

    @Override
    public List<LotDto> getLotOfBuyerStatusTrue(Integer buyerId, LocalDate currentDate) {
        return null;
    }

    @Override
    public List<LotDto> getLotOfBuyerStatusFalse(Integer buyerId, LocalDate currentDate) {
        return null;
    }

    @Override
    public void getLotOfBuyerWithoutSoldLot(Integer buyerId, LocalDate currentDate) {

    }

    @Override
    public BigDecimal summationOfBuyerLotPricesInOrderConfirmation(Integer buyerId, LocalDate currentDate) {
        return null;
    }

    @Override
    public BigDecimal summationOfBuyerLotPricesInOrder(Integer buyerId, LocalDate currentDate) {
        return null;
    }

    @Override
    public List<LotDto> getLotOfBuyerForPurchase(Integer buyerId, LocalDate currentDate) {
        return null;
    }

    @Override
    public void returnStatusTrueFromPurchase(Integer buyerId) {

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
