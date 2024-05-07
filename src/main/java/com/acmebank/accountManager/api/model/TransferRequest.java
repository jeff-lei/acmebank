package com.acmebank.accountManager.api.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class TransferRequest {
    private String fromAccount;

    private String toAccount;

    //@JsonFormat(shape=JsonFormat.Shape.STRING)
    private BigDecimal amount;
}
