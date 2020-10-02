package com.advertbaas.eventsourcing.exceptions;

import java.util.UUID;

/**
 * @author danushka
 * 10/3/2020
 */
public class AccountNotFoundException extends Throwable {
    public AccountNotFoundException(UUID uuid) {
        super("cannot find account number : " + uuid);
    }
}
