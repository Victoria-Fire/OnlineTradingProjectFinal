package by.academy.it.reunova.dto.converter.interfaces;

import by.academy.it.reunova.dto.LotDto;
import by.academy.it.reunova.entity.Lot;

public interface LotConverter {

    LotDto toLotDto(Lot lot);
    Lot toLot(LotDto lotDto);
}
