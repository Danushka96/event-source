package com.advertbaas.eventsourcing.events;

import lombok.Value;

import java.math.BigDecimal;
import java.util.UUID;

/**
 * @author danushka
 * 10/3/2020
 */
@Value
public class MoneyCreditedEvent {
    private final UUID id;
    private final BigDecimal creditAmount;
}
