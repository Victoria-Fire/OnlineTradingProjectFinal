package by.academy.it.reunova.dto;

import by.academy.it.reunova.entity.Buyer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class LotDto {
    private Integer id;
    private String nameLot;
    private String descriptionLot;
    private LocalDate startDateLot;
    private LocalDate endDateLot;
    private BigDecimal startPriceLot;
    private BigDecimal endPriceLot;
    private Boolean statusLot;
    private BigDecimal priceLot;
    private List<Buyer> buyers;
}
