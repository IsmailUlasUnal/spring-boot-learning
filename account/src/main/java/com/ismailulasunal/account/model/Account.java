package com.ismailulasunal.account.model;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

@Entity
public class Account {

    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id;

    private BigDecimal balance;
    private LocalDateTime creationDate;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @OneToMany(mappedBy = "account", fetch = FetchType.LAZY)
    private Set<Transaction> transactions;

    public Account(Customer customer, BigDecimal balance, LocalDateTime creationDate) {
        this.customer = customer;
        this.balance = balance;
        this.creationDate = creationDate;
        transactions = new HashSet<>();
    }

    public Account() {
    }

    public Account(BigDecimal balance, LocalDateTime creationDate, Customer customer, Set<Transaction> transactions) {
        this.balance = balance;
        this.creationDate = creationDate;
        this.customer = customer;
        this.transactions = transactions;
    }

    public Account(UUID id, BigDecimal balance, LocalDateTime creationDate, Customer customer, Set<Transaction> transactions) {
        this.id = id;
        this.balance = balance;
        this.creationDate = creationDate;
        this.customer = customer;
        this.transactions = transactions;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Account account)) return false;
        return Objects.equals(balance, account.balance) && Objects.equals(creationDate, account.creationDate) && Objects.equals(customer, account.customer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(balance, creationDate);
    }


    public BigDecimal getBalance() {
        return balance;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Set<Transaction> getTransactions() {
        return transactions;
    }

    public UUID getId() {
        return id;
    }
}
