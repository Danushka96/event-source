package com.advertbaas.eventsourcing.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.UUID;

/**
 * @author danushka
 * 10/3/2020
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class BankAccount {
    @Id
    private UUID id;
    private String owner;
    private BigDecimal balance;
}
