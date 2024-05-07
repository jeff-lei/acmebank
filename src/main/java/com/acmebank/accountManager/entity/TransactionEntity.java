package com.acmebank.accountManager.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;
import java.util.Date;

@Data
@Entity
@Table(name = "transaction")
public class TransactionEntity {
    @Id
    @Column(name = "id", nullable = false)
    @SequenceGenerator(name = "transaction_id_seq",
            sequenceName = "transaction_id_seq_generator",
            initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "transaction_id_seq")
    private Long id;

    @Column(name = "from_account_number")
    private String fromAccountNumber;

    @Column(name = "to_account_number")
    private String toAccountNumber;

    @Column(name = "amount")
    private BigDecimal amount;

    @Column(name ="created_date")
    private Date createdDate;
}
