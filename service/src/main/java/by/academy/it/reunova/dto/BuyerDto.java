package by.academy.it.reunova.dto;

import by.academy.it.reunova.entity.Lot;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class BuyerDto {
    private Integer id;
    private String nameBuyer;
    private String surnameBuyer;
    private List<Lot> lots;

}
