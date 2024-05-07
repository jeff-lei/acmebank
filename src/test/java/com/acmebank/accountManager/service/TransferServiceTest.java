package com.acmebank.accountManager.service;

import com.acmebank.accountManager.AccountManagerApplication;
import com.acmebank.accountManager.domain.Transaction;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import java.math.BigDecimal;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest(classes = AccountManagerApplication.class)
@ActiveProfiles("test")
@Sql(scripts = "/test-data.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
public class TransferServiceTest {

    @Autowired
    TransferService transferService;

    @Test
    void testMakeTransfer_GivenTwoExistingAccountAndEnoughBalance_shouldCompleteTransferWithNewTransactionId() throws Exception {
        Transaction newTransaction = transferService.createTransfer("12345678", "88888888", new BigDecimal(100));

        assertThat(newTransaction).isNotNull();
        assertThat(newTransaction.getId()).isGreaterThan(0);//a new db row should have a non-zero id from seq generator
        assertThat(newTransaction.getFromAccountNumber()).isEqualTo("12345678");
        assertThat(newTransaction.getToAccountNumber()).isEqualTo("88888888");
    }

    @Test
    void testMakeTransfer_GivenNonExistingFromAccount_shouldThrowException() {
        Exception exception = assertThrows(Exception.class, () -> {
             transferService.createTransfer("34567890", "88888888", new BigDecimal(100));
        });

        String actualMessage = exception.getMessage();
        assertThat(actualMessage).isEqualTo("From account not found");
    }

    @Test
    void testMakeTransfer_GivenTwoExistingAccounts_WhenTransferAmountExceedFromAccountBalance_shouldThrowException() {
        Exception exception = assertThrows(Exception.class, () -> {
            transferService.createTransfer("12345678", "88888888", new BigDecimal(1000001));
        });

        String actualMessage = exception.getMessage();
        assertThat(actualMessage).isEqualTo("From account does not have enough fund");
    }

    @Test
    void testMakeTransfer_GivenNonExistingToAccount_shouldThrowException() {
        Exception exception = assertThrows(Exception.class, () -> {
            transferService.createTransfer("12345678", "99999999", new BigDecimal(100));
        });

        String actualMessage = exception.getMessage();
        assertThat(actualMessage).isEqualTo("To account not found");
    }
}
