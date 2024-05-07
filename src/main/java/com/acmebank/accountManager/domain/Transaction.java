package com.acmebank.accountManager.domain;

import jakarta.persistence.Column;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class Transaction {
    private Long id;

    private String fromAccountNumber;

    private String toAccountNumber;

    private BigDecimal amount;

    private Date createdDate;
}
