package com.acmebank.accountManager.service;

import com.acmebank.accountManager.AccountManagerApplication;
import com.acmebank.accountManager.domain.Account;
import com.acmebank.accountManager.mapper.AccountMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = AccountManagerApplication.class)
@ActiveProfiles("test")
@Sql(scripts = "/test-data.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
public class AccountServiceTest {
    @Autowired
    AccountService accountService;

    @Autowired
    AccountMapper accountMapper;

    @Test
    void test_findAccountByAccountNumber_GivenValidAccountNumber_shouldReturnAccount(){
        Account retrievedAccount = accountService.getAccountByAccountNumber("12345678");

        assertThat(retrievedAccount).isNotNull();
        assertThat(retrievedAccount.getAccountNumber()).isEqualTo("12345678");
    }
}
