package com.acmebank.accountManager.mapper;

import com.acmebank.accountManager.domain.Transaction;
import com.acmebank.accountManager.entity.TransactionEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TransactionMapper {
    Transaction fromTransactionEntity(TransactionEntity transactionEntity);
}
