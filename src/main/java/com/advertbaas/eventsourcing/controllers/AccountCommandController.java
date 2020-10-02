package com.advertbaas.eventsourcing.controllers;

import com.advertbaas.eventsourcing.dtos.AccountCreationDTO;
import com.advertbaas.eventsourcing.dtos.MoneyAmountDTO;
import com.advertbaas.eventsourcing.models.BankAccount;
import com.advertbaas.eventsourcing.services.AccountCommandService;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.CompletableFuture;

import static org.springframework.http.HttpStatus.CREATED;

/**
 * @author danushka
 * 10/3/2020
 */
@RestController
@RequestMapping(value = "/accounts")
@Api(value = "Bank Account Commands", description = "Bank Account Commands API")
@AllArgsConstructor
public class AccountCommandController {
    private final AccountCommandService accountCommandService;

    @PostMapping
    @ResponseStatus(value = CREATED)
    public CompletableFuture<BankAccount> createAccount(@RequestBody AccountCreationDTO creationDTO) {
        return this.accountCommandService.createAccount(creationDTO);
    }

    @PutMapping(value = "/credit/{accountId}")
    public CompletableFuture<String> creditMoneyToAccount(@PathVariable(value = "accountId") String accountId,
                                                          @RequestBody MoneyAmountDTO moneyCreditDTO) {
        return this.accountCommandService.creditMoneyToAccount(accountId, moneyCreditDTO);
    }

    @PutMapping(value = "/debit/{accountId}")
    public CompletableFuture<String> debitMoneyFromAccount(@PathVariable(value = "accountId") String accountId,
                                                           @RequestBody MoneyAmountDTO moneyDebitDTO) {
        return this.accountCommandService.debitMoneyFromAccount(accountId, moneyDebitDTO);
    }

}
