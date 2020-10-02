package com.advertbaas.eventsourcing.dtos;

import lombok.Value;

import java.math.BigDecimal;

/**
 * @author danushka
 * 10/3/2020
 */
@Value
public class AccountCreationDTO {
    private final BigDecimal initialBalance;
    private final String owner;
}
