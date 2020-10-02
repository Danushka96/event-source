package com.advertbaas.eventsourcing.repository;

import com.advertbaas.eventsourcing.models.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

/**
 * @author danushka
 * 10/3/2020
 */
public interface BankAccountRepository extends JpaRepository<BankAccount, UUID> {
}
