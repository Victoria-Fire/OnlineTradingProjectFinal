package by.academy.it.reunova.dto.converter.interfaces;

import by.academy.it.reunova.dto.BuyerDto;
import by.academy.it.reunova.entity.Buyer;

public interface BuyerConverter {

    BuyerDto toBuyerDto(Buyer buyer);
    Buyer toBuyer(BuyerDto buyerDto);

}
