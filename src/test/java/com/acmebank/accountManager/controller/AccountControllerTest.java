package com.acmebank.accountManager.controller;

import com.acmebank.accountManager.AccountManagerApplication;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(classes = AccountManagerApplication.class)
@ActiveProfiles("test")
@Sql(scripts = "/test-data.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@AutoConfigureMockMvc
public class AccountControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testGetBalance_GivenValidAccountNumber_ShouldReturnAccountBalance() throws Exception{
        mockMvc.perform(get("/api/accounts/12345678"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.accountBalance").value("1000000.00"));

    }

    @Test
    public void testGetBalance_GivenNonExistingAccountNumber_ShouldReturnHttp404NotFound() throws Exception {
        mockMvc.perform(get("/api/accounts/99999999"))
                .andExpect(status().is(404));
    }

    @Test
    public void testGetBalance_GivenBadFormatAccountNumber_ShouldReturnHttp400BadRequest() throws Exception {
        mockMvc.perform(get("/api/accounts/A9999999"))
                .andExpect(status().is(400));
    }
}
