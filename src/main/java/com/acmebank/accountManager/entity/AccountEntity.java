package com.acmebank.accountManager.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Entity
@Table(name = "account")
public class AccountEntity {

    @Id
    @Column(name = "id", nullable = false)
    @SequenceGenerator(name = "account_id_seq",
            sequenceName = "account_id_seq_generator",
            initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "account_id_seq")
    private Long id;

    @Column(name = "account_number")
    private String accountNumber;

    @Column(name = "account_balance")
    private BigDecimal accountBalance;

    //for simplicity reason, keeping these kind of additional information away
    //isActive, createdData, status
    //name, description, or some more. these can be kept in a separate "account_information" table
}
