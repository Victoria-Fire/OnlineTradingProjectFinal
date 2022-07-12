package by.academy.it.reunova.interfaces;

import by.academy.it.reunova.dto.BuyerDto;

import java.util.List;

public interface BuyerService {
    void saveBuyer(BuyerDto buyerDto);
    void deleteBuyer(Integer buyerId);
    BuyerDto findBuyerById(Integer buyerId);
    List<BuyerDto> findAllBuyerDto();
}
