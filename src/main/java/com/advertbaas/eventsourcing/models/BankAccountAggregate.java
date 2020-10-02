package com.advertbaas.eventsourcing.models;

import com.advertbaas.eventsourcing.commands.CreateAccountCommand;
import com.advertbaas.eventsourcing.commands.CreditMoneyCommand;
import com.advertbaas.eventsourcing.commands.DebitMoneyCommand;
import com.advertbaas.eventsourcing.events.AccountCreatedEvent;
import com.advertbaas.eventsourcing.events.MoneyCreditedEvent;
import com.advertbaas.eventsourcing.events.MoneyDebitedEvent;
import com.advertbaas.eventsourcing.exceptions.InsufficientBalanceException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

import java.math.BigDecimal;
import java.util.UUID;

/**
 * @author danushka
 * 10/3/2020
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Aggregate
public class BankAccountAggregate {
    @AggregateIdentifier
    private UUID id;
    private String owner;
    private BigDecimal balance;

    @CommandHandler
    public BankAccountAggregate(CreateAccountCommand cmd){
        AggregateLifecycle.apply(
                new AccountCreatedEvent(
                        cmd.getId(),
                        cmd.getIntialBalance(),
                        cmd.getOwner()
                )
        );
    }

    @EventSourcingHandler
    public void on(AccountCreatedEvent event){
        this.id = event.getId();
        this.owner = event.getOwner();
        this.balance = event.getInitialBalance();
    }

    @CommandHandler
    public void handle(CreditMoneyCommand cmd){
        AggregateLifecycle.apply(
                new MoneyCreditedEvent(
                        cmd.getAccountId(),
                        cmd.getCreditAmount()
                )
        );
    }

    @EventSourcingHandler
    public void on(MoneyCreditedEvent event){
        this.balance = this.balance.add(event.getCreditAmount());
    }

    @CommandHandler
    public void handle(DebitMoneyCommand cmd){
        AggregateLifecycle.apply(
                new MoneyDebitedEvent(
                        cmd.getAccountId(),
                        cmd.getDebitAmount()
                )
        );
    }

    @EventSourcingHandler
    public void on(MoneyDebitedEvent event) throws InsufficientBalanceException {
        if (balance.compareTo(event.getDebitAmount()) < 0){
            throw new InsufficientBalanceException(event.getId(), event.getDebitAmount());
        }
        this.balance = this.balance.subtract(event.getDebitAmount());
    }
}
