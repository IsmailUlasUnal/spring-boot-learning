package com.ismailulasunal.account.service;

import com.ismailulasunal.account.dto.AccountDto;
import com.ismailulasunal.account.dto.CreateAccountRequest;
import com.ismailulasunal.account.model.Account;
import com.ismailulasunal.account.model.Customer;
import com.ismailulasunal.account.model.Transaction;
import com.ismailulasunal.account.repository.AccountRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
public class AccountService {
    private final AccountRepository accountRepository;
    private final CustomerService customerService;
    private final TransactionService transactionService;

    public AccountService(AccountRepository accountRepository,
                          CustomerService customerService,
                          TransactionService transactionService) {
        this.accountRepository = accountRepository;
        this.customerService = customerService;
        this.transactionService = transactionService;
    }

    public AccountDto createAccount(CreateAccountRequest createAccountRequest) {
        Customer customer = customerService.findCustomerById(createAccountRequest.customerId());

        Account account = new Account(
                customer,
                createAccountRequest.initialCredit(),
                LocalDateTime.now()
        );

        if (createAccountRequest.initialCredit().compareTo(BigDecimal.ZERO) > 0) {
            Transaction transaction = transactionService.initiateMoney(account, createAccountRequest.initialCredit());
            account.getTransactions().add(transaction);
        }

        return AccountDto.convert(accountRepository.save(account));
    }
}
