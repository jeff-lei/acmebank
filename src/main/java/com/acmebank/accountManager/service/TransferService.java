package com.acmebank.accountManager.service;

import com.acmebank.accountManager.domain.Account;
import com.acmebank.accountManager.domain.Transaction;
import com.acmebank.accountManager.entity.AccountEntity;
import com.acmebank.accountManager.entity.TransactionEntity;
import com.acmebank.accountManager.mapper.TransactionMapper;
import com.acmebank.accountManager.repository.AccountEntityRepository;
import com.acmebank.accountManager.repository.TransactionEntityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class TransferService {

    private final AccountEntityRepository accountEntityRepository;

    private final TransactionEntityRepository transactionEntityRepository;

    private final TransactionMapper transactionMapper;

    @Transactional
    public Transaction createTransfer(String fromAccount, String toAccount, BigDecimal amount) throws Exception{
        //Load the two accounts to make sure they exist
        AccountEntity debitAccount = accountEntityRepository.getByAccountNumber(fromAccount).orElse(null);
        AccountEntity creditAccount = accountEntityRepository.getByAccountNumber(toAccount).orElse(null);

        //do checking to make sure both accounts exist and contain enough fund for transfer
        //For simplicity I reuse the general Exception, in real system we would have created our own
        //exception classes and the mechanism
        if (debitAccount == null) throw new Exception("From account not found");
        if (creditAccount == null) throw new Exception("To account not found");
        if (debitAccount.getAccountBalance().compareTo(amount) < 0)
            throw new Exception("From account does not have enough fund");

        //prepare all data entities to persist
        TransactionEntity newTransaction = new TransactionEntity();
        newTransaction.setFromAccountNumber(fromAccount);
        newTransaction.setToAccountNumber(toAccount);
        newTransaction.setAmount(amount);
        newTransaction.setCreatedDate(new Date());
        //we may also add other fields such as some alphanumeric "Transfer Reference Number" if needed - which it leads to some other mechanism to generate such reference

        transactionEntityRepository.save(newTransaction);

        debitAccount.setAccountBalance(debitAccount.getAccountBalance().subtract(amount));
        accountEntityRepository.save(debitAccount);

        creditAccount.setAccountBalance(creditAccount.getAccountBalance().add(amount));
        accountEntityRepository.save(creditAccount);

        return transactionMapper.fromTransactionEntity(newTransaction); //return the new entity that contains the
    }
}
