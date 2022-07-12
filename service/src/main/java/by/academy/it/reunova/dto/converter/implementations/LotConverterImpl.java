package by.academy.it.reunova.dto.converter.implementations;

import by.academy.it.reunova.dto.LotDto;
import by.academy.it.reunova.dto.converter.interfaces.LotConverter;
import by.academy.it.reunova.entity.Lot;

import java.math.BigDecimal;
import java.time.LocalDate;

public class LotConverterImpl implements LotConverter {

    @Override
    public LotDto toLotDto(Lot lot) {

        LotDto lotDto = LotDto.builder()
                .id(lot.getId())
                .nameLot(lot.getNameLot())
                .descriptionLot(lot.getDescriptionLot())
                .startDateLot(lot.getStartDateLot())
                .endDateLot(lot.getEndDateLot())
                .startPriceLot(lot.getStartPriceLot())
                .endPriceLot(lot.getEndPriceLot())
                .statusLot(lot.getStatusLot())
//                .priceLot(lotService.getCurrentPrice(lot.getId(), LocalDate.now()))
                .priceLot(BigDecimal.valueOf(1.00))
//                .buyers(lot.getBuyers())
                .build();

        return lotDto;
    }

    @Override
    public Lot toLot(LotDto lotDto) {

        Lot lot = Lot.builder()
                .id(lotDto.getId())
                .nameLot(lotDto.getNameLot())
                .descriptionLot(lotDto.getDescriptionLot())
                .startDateLot(lotDto.getStartDateLot())
//                .endDateLot(lotDto.getEndDateLot())
                .startPriceLot(lotDto.getStartPriceLot())
                .endPriceLot(lotDto.getEndPriceLot())
                .statusLot(lotDto.getStatusLot())
//                .buyers(lot.getBuyers())
                .build();

        return lot;
    }
}
