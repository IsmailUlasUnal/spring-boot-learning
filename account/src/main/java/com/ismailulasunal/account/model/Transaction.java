package com.ismailulasunal.account.model;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;


@Entity
public class Transaction {

    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id;
    private TransactionType transactionType = TransactionType.INITIAL;
    private BigDecimal amount;
    private LocalDateTime transactionDate;
    @ManyToOne(fetch = FetchType.LAZY, optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "account_id", nullable = false)
    private Account account;

    public UUID getId() {
        return id;
    }
    public TransactionType getTransactionType() {
        return transactionType;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public LocalDateTime getTransactionDate() {
        return transactionDate;
    }

    public Account getAccount() {
        return account;
    }

    public Transaction(BigDecimal amount, LocalDateTime transactionDate, Account account) {
        this.amount = amount;
        this.transactionDate = transactionDate;
        this.account = account;
    }


    public Transaction(UUID id, TransactionType transactionType, BigDecimal amount, LocalDateTime transactionDate, Account account) {
        this.id = id;
        this.transactionType = transactionType;
        this.amount = amount;
        this.transactionDate = transactionDate;
        this.account = account;
    }

    public Transaction(TransactionType transactionType, BigDecimal amount, LocalDateTime transactionDate, Account account) {
        this.transactionType = transactionType;
        this.amount = amount;
        this.transactionDate = transactionDate;
        this.account = account;
    }

    public Transaction() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Transaction that)) return false;
        return transactionType == that.transactionType && Objects.equals(amount, that.amount) && Objects.equals(transactionDate, that.transactionDate) && Objects.equals(account, that.account);
    }

    @Override
    public int hashCode() {
        return Objects.hash(transactionType, amount, transactionDate);
    }
}