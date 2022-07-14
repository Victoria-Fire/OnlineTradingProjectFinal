package by.academy.it.reunova.interfaces;

import by.academy.it.reunova.dto.LotDto;

import java.util.List;

public interface OrderHistoryService {
    void buyLots(Integer buyerId);
    void buyOneLot(Integer buyerId, Integer lotId);
    List<LotDto> findAllLotDtoInOrderHistory();
}
