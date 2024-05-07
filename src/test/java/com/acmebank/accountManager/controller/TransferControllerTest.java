package com.acmebank.accountManager.controller;

import com.acmebank.accountManager.AccountManagerApplication;
import com.acmebank.accountManager.domain.Account;
import com.acmebank.accountManager.service.AccountService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(classes = AccountManagerApplication.class)
@ActiveProfiles("test")
@Sql(scripts = "/test-data.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@AutoConfigureMockMvc
public class TransferControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    AccountService accountService;

    @Test
    public void testPostTransfer_GivenValidTransferRequestPayload_shouldCompleteTrasfer() throws Exception {
        StringBuilder payloadBuilder = new StringBuilder();
        payloadBuilder.append("{\"fromAccount\": \"12345678\", ")
                .append("\"toAccount\": \"88888888\", ")
                .append("\"amount\": 1000.00 }");

        mockMvc.perform(post("/api/transfers/").contentType(MediaType.APPLICATION_JSON)
                .content(payloadBuilder.toString())
                ).andExpect(status().isCreated());

        Account debitAccount = accountService.getAccountByAccountNumber("12345678");
        assertThat(debitAccount).isNotNull();
        assertThat(debitAccount.getAccountBalance()).isEqualTo("999000.00");

        Account creditAccount = accountService.getAccountByAccountNumber("88888888");
        assertThat(creditAccount).isNotNull();
        assertThat(creditAccount.getAccountBalance()).isEqualTo("1001000.00");
    }

    @Test
    public void testPostTransfer_GivenInValidFromAccountPayload_shouldReturnBadRequest() throws Exception {
        StringBuilder payloadBuilder = new StringBuilder();
        payloadBuilder.append("{\"fromAccount\": \"123456789\", ")
                .append("\"toAccount\": \"88888888\", ")
                .append("\"amount\": 1000.00 }");

        mockMvc.perform(post("/api/transfers/").contentType(MediaType.APPLICATION_JSON)
                .content(payloadBuilder.toString())
        ).andExpect(status().isBadRequest());
    }


    @Test
    public void testPostTransfer_GivenInValidToAccountPayload_shouldReturnBadRequest() throws Exception {
        StringBuilder payloadBuilder = new StringBuilder();
        payloadBuilder.append("{\"fromAccount\": \"12345678\", ")
                .append("\"toAccount\": \"888888889\", ")
                .append("\"amount\": 1000.00 }");

        mockMvc.perform(post("/api/transfers/").contentType(MediaType.APPLICATION_JSON)
                .content(payloadBuilder.toString())
        ).andExpect(status().isBadRequest());
    }
}
