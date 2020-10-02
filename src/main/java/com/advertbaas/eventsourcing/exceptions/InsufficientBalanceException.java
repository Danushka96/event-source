package com.advertbaas.eventsourcing.exceptions;

import java.math.BigDecimal;
import java.util.UUID;

/**
 * @author danushka
 * 10/3/2020
 */
public class InsufficientBalanceException extends Throwable {
    public InsufficientBalanceException(UUID accountId, BigDecimal debitAmount) {
        super("Insufficient Balance: Cannot debit " + debitAmount +
                " from account number [" + accountId.toString() + "]");
    }
}
