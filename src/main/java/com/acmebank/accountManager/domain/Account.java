package com.acmebank.accountManager.domain;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class Account {
    private String accountNumber;

    private BigDecimal accountBalance;
}
