package com.advertbaas.eventsourcing.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * @author danushka
 * 10/3/2020
 */
@Data
@NoArgsConstructor
public class MoneyAmountDTO {
    private BigDecimal amount;
}
