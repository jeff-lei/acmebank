package com.acmebank.accountManager.service;

import com.acmebank.accountManager.domain.Account;
import com.acmebank.accountManager.entity.AccountEntity;
import com.acmebank.accountManager.mapper.AccountMapper;
import com.acmebank.accountManager.repository.AccountEntityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountEntityRepository accountEntityRepository;

    private final AccountMapper accountMapper;
    public Account getAccountByAccountNumber(String accountNumber) {
        AccountEntity accountEntity = accountEntityRepository.getByAccountNumber(accountNumber).orElse(null);

        if (accountEntity != null) {
            return accountMapper.fromAccountEntity(accountEntity);
        }

        return null; //I chose to return null when the account not found, let the caller (controller) to make
        //decision if an HTTP 404 or other should be throw back to the API client
    }
}
