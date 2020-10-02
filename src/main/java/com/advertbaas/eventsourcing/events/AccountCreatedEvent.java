package com.advertbaas.eventsourcing.events;

import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

/**
 * @author danushka
 * 10/3/2020
 */
@Data
public class AccountCreatedEvent {
    public final UUID id;
    public final BigDecimal initialBalance;
    public final String owner;
}
