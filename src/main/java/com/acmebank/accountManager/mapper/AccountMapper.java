package com.acmebank.accountManager.mapper;

import com.acmebank.accountManager.domain.Account;
import com.acmebank.accountManager.entity.AccountEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AccountMapper {
    Account fromAccountEntity(AccountEntity accountEntity);
}
