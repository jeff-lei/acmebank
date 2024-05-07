package com.acmebank.accountManager;

import com.acmebank.accountManager.repository.AccountEntityRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class AccountManagerApplicationTests {

    @Autowired
    private AccountEntityRepository accountEntityRepository;

    @Test
    void contextLoads() throws Exception {
        assertThat(accountEntityRepository).isNotNull();

    }

}
