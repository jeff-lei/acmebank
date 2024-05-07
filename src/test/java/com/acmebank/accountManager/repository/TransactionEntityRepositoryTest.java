package com.acmebank.accountManager.repository;

import com.acmebank.accountManager.AccountManagerApplication;
import com.acmebank.accountManager.entity.TransactionEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

import java.math.BigDecimal;
import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = AccountManagerApplication.class)
@ActiveProfiles("test")
@Sql(scripts = "/test-data.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
public class TransactionEntityRepositoryTest {
    @Autowired
    TransactionEntityRepository transactionEntityRepository;

    @Test
    void getAllEntities() {
        Iterable<TransactionEntity> allEntities = transactionEntityRepository.findAll();
        for (TransactionEntity allEntity : allEntities) {
            System.out.println(allEntity);
        }
    }

    @Test
    void testGetTransactionEntityByValidId_shouldReturnTransactionEntity() {
        TransactionEntity transactionEntity = transactionEntityRepository.findById(1000L).get();

        assertThat(transactionEntity).isNotNull();
        assertThat(transactionEntity.getFromAccountNumber()).isEqualTo("12345678");
    }

    @Test
    void testCreateTransactionEntityAndSaveToRepository() {
        TransactionEntity transactionEntity = new TransactionEntity();
        transactionEntity.setFromAccountNumber("12345678");
        transactionEntity.setToAccountNumber("88888888");
        transactionEntity.setAmount(new BigDecimal(100.00));
        transactionEntity.setCreatedDate(new Date());

        transactionEntityRepository.save(transactionEntity);

        assertThat(transactionEntity.getId()).isGreaterThan(0);
    }
}
