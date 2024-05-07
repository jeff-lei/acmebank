package com.acmebank.accountManager.controller;

import com.acmebank.accountManager.api.model.AccountBalanceResponse;
import com.acmebank.accountManager.domain.Account;
import com.acmebank.accountManager.service.AccountService;
import com.acmebank.accountManager.utility.Validator;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class AccountController {
    private final AccountService accountService;

    @GetMapping(value = "/api/accounts/{accountNumber}", produces = "application/json")
    public ResponseEntity<AccountBalanceResponse> getAccountBalance(@PathVariable String accountNumber) {
        //validate accountNumber patter, if not valid return badrequest
        if (!Validator.isValidAccountFormat(accountNumber)) return ResponseEntity.badRequest().build();

        Account account = accountService.getAccountByAccountNumber(accountNumber);

        if (account != null) {
            AccountBalanceResponse response = new AccountBalanceResponse();
            response.setAccountBalance(account.getAccountBalance().toString()); //do some formatting
            return ResponseEntity.ok(response);
        }

        return ResponseEntity.notFound().build();
    }
}
