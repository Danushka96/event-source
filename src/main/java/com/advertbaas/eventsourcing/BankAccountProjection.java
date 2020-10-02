package com.advertbaas.eventsourcing;

import com.advertbaas.eventsourcing.events.AccountCreatedEvent;
import com.advertbaas.eventsourcing.events.MoneyCreditedEvent;
import com.advertbaas.eventsourcing.events.MoneyDebitedEvent;
import com.advertbaas.eventsourcing.exceptions.AccountNotFoundException;
import com.advertbaas.eventsourcing.models.BankAccount;
import com.advertbaas.eventsourcing.query.FindBankAccountQuery;
import com.advertbaas.eventsourcing.repository.BankAccountRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * @author danushka
 * 10/3/2020
 */
@Slf4j
@RequiredArgsConstructor
@Component
public class BankAccountProjection {
    private final BankAccountRepository bankAccountRepository;

    @EventHandler
    public void on(AccountCreatedEvent event){
        log.debug("Handling a Bank Account creation command {}", event.getId());
        BankAccount bankAccount = new BankAccount(event.getId(), event.getOwner(), event.getInitialBalance());
        this.bankAccountRepository.save(bankAccount);
    }

    @EventHandler
    public void on(MoneyCreditedEvent event) throws AccountNotFoundException {
        log.debug("Handling an Account Credit command {}", event.getId());
        Optional<BankAccount> optionalBankAccount = this.bankAccountRepository.findById(event.getId());
        if (optionalBankAccount.isPresent()) {
            BankAccount bankAccount = optionalBankAccount.get();
            bankAccount.setBalance(bankAccount.getBalance().add(event.getCreditAmount()));
            this.bankAccountRepository.save(bankAccount);
        } else {
            throw new AccountNotFoundException(event.getId());
        }
    }

    @EventHandler
    public void on(MoneyDebitedEvent event) throws AccountNotFoundException {
        log.debug("Handling an Account Debit command {}", event.getId());
        Optional<BankAccount> optionalBankAccount = this.bankAccountRepository.findById(event.getId());
        if (optionalBankAccount.isPresent()) {
            BankAccount bankAccount = optionalBankAccount.get();
            bankAccount.setBalance(bankAccount.getBalance().subtract(event.getDebitAmount()));
            this.bankAccountRepository.save(bankAccount);
        } else {
            throw new AccountNotFoundException(event.getId());
        }
    }

    @QueryHandler
    public BankAccount handle(FindBankAccountQuery query) {
        log.debug("Handling FindBankAccountQuery query: {}", query);
        return this.bankAccountRepository.findById(query.getAccountId()).orElse(null);
    }

}
