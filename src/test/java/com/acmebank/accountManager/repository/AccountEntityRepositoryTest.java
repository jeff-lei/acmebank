package com.acmebank.accountManager.repository;

import com.acmebank.accountManager.AccountManagerApplication;
import com.acmebank.accountManager.entity.AccountEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = AccountManagerApplication.class)
@ActiveProfiles("test")
@Sql(scripts = "/test-data.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
public class AccountEntityRepositoryTest {

    @Autowired
    private AccountEntityRepository accountEntityRepository;

    //retrieve an account with a valid account number, should return the accountEntity
    @Test
    void testGetAccountByAccountNumber_shouldReturnAccountEntity() {
        AccountEntity accountEntity = accountEntityRepository.getByAccountNumber("12345678").orElse(null);

        assertThat(accountEntity).isNotNull();
        assertThat(accountEntity.getAccountNumber()).isEqualTo("12345678");
    }
}
