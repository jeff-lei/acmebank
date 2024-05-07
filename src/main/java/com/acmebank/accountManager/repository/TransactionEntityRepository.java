package com.acmebank.accountManager.repository;

import com.acmebank.accountManager.entity.TransactionEntity;
import org.springframework.data.repository.CrudRepository;

public interface TransactionEntityRepository extends CrudRepository<TransactionEntity, Long> {

}
