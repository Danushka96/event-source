package com.advertbaas.eventsourcing.services;

import com.advertbaas.eventsourcing.commands.CreateAccountCommand;
import com.advertbaas.eventsourcing.commands.CreditMoneyCommand;
import com.advertbaas.eventsourcing.commands.DebitMoneyCommand;
import com.advertbaas.eventsourcing.dtos.AccountCreationDTO;
import com.advertbaas.eventsourcing.dtos.MoneyAmountDTO;
import com.advertbaas.eventsourcing.models.BankAccount;
import lombok.AllArgsConstructor;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

import static com.advertbaas.eventsourcing.services.ServiceUtils.formatUuid;

/**
 * @author danushka
 * 10/3/2020
 */
@Service
@AllArgsConstructor
public class AccountCommandService {
    private final CommandGateway commandGateway;
    
    public CompletableFuture<BankAccount> createAccount(AccountCreationDTO creationDTO){
        return this.commandGateway.send(new CreateAccountCommand(
                UUID.randomUUID(),
                creationDTO.getInitialBalance(),
                creationDTO.getOwner()
        ));
    }

    public CompletableFuture<String> creditMoneyToAccount(String accountId,
                                                          MoneyAmountDTO moneyCreditDTO) {
        return this.commandGateway.send(new CreditMoneyCommand(
                formatUuid(accountId),
                moneyCreditDTO.getAmount()
        ));
    }

    public CompletableFuture<String> debitMoneyFromAccount(String accountId,
                                                           MoneyAmountDTO moneyDebitDTO) {
        return this.commandGateway.send(new DebitMoneyCommand(
                formatUuid(accountId),
                moneyDebitDTO.getAmount()
        ));
    }
}
