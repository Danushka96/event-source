package com.advertbaas.eventsourcing.commands;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import java.math.BigDecimal;
import java.util.UUID;

/**
 * @author danushka
 * 10/3/2020
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreditMoneyCommand {
    @TargetAggregateIdentifier
    private UUID accountId;
    private BigDecimal creditAmount;
}
