package by.academy.it.reunova.dto.converter.implementations;

import by.academy.it.reunova.dto.BuyerDto;
import by.academy.it.reunova.dto.converter.interfaces.BuyerConverter;
import by.academy.it.reunova.entity.Buyer;

public class BuyerConverterImpl implements BuyerConverter {

    @Override
    public BuyerDto toBuyerDto(Buyer buyer) {
        BuyerDto buyerDto = BuyerDto.builder()
                .id(buyer.getId())
                .nameBuyer(buyer.getNameBuyer())
                .surnameBuyer(buyer.getSurnameBuyer())
//                .lots(buyer.getLots().stream().map(Lot::getNameLot).collect(joining(" , ")))
//                .lots(buyer.getLots().stream().map(Lot).collect(toList()))
//                .lots(buyer.getLots())
//                .lots(buyer.getLots().stream().collect(Collectors.toList()))
                .build();

        return buyerDto;

    }

    @Override
    public Buyer toBuyer(BuyerDto buyerDto) {
        Buyer buyer = Buyer.builder()
                .id(buyerDto.getId())
                .nameBuyer(buyerDto.getNameBuyer())
                .surnameBuyer(buyerDto.getSurnameBuyer())
                .build();

        return buyer;
    }
}

