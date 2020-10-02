package com.advertbaas.eventsourcing.query;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

/**
 * @author danushka
 * 10/3/2020
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FindBankAccountQuery {
    private UUID accountId;
}
