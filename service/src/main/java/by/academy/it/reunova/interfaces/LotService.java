package by.academy.it.reunova.interfaces;

import by.academy.it.reunova.dto.LotDto;

import java.math.BigDecimal;
import java.util.List;

public interface LotService {
    void saveLot(LotDto lotDto);
    void deleteLot(Integer lotId);
    void deleteLotAnyway(Integer lotId);
    LotDto findLotById(Integer lotId);
    List<LotDto> findAllLotDto();
    List<LotDto> findAllLotDtoWithoutOrderHistory();
    List<LotDto> findLotDtoForSale();
    List<LotDto> getLotOfBuyer(Integer buyerId);
    List<Integer> getLotListIdOfBuyer(Integer buyerId);
    List<LotDto> getLotOfBuyerStatusTrue(Integer buyerId);
    List<LotDto> getLotOfBuyerStatusFalse(Integer buyerId);
    void getLotOfBuyerWithoutSoldLot(Integer buyerId);
    BigDecimal summationOfBuyerLotPricesInOrderConfirmation(Integer buyerId);
    BigDecimal summationOfBuyerLotPricesInOrder(Integer buyerId);
    List<LotDto> getLotOfBuyerForPurchase(Integer buyerId);
    void returnStatusTrueFromPurchase(Integer buyerId);
}
