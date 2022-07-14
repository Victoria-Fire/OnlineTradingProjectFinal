package by.academy.it.reunova.dto.converter.implementations;

import by.academy.it.reunova.dto.LotDto;
import by.academy.it.reunova.dto.converter.interfaces.LotConverter;
import by.academy.it.reunova.entity.Lot;
import by.academy.it.reunova.price.PriceCalculator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class LotConverterImpl implements LotConverter {

    private final PriceCalculator priceCalculator;

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
                .priceLot(priceCalculator.getCurrentPrice(lot.getId()))
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
                .endDateLot(lotDto.getEndDateLot())
                .startPriceLot(lotDto.getStartPriceLot())
                .endPriceLot(lotDto.getEndPriceLot())
                .statusLot(lotDto.getStatusLot())
                .build();
        return lot;
    }
}
