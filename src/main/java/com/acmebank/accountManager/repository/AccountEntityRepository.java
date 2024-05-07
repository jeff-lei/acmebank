package com.acmebank.accountManager.repository;

import com.acmebank.accountManager.entity.AccountEntity;
import org.springframework.data.repository.CrudRepository;
import java.util.Optional;

public interface AccountEntityRepository extends CrudRepository<AccountEntity, Long> {

    public Optional<AccountEntity> getByAccountNumber(String accountNumber);
}
