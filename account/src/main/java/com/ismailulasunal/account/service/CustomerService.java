package com.ismailulasunal.account.service;

import com.ismailulasunal.account.exception.CustomerNotFoundException;
import com.ismailulasunal.account.model.Customer;
import com.ismailulasunal.account.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CustomerService {
    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    protected Customer findCustomerById(UUID id) {
        return customerRepository.findById(id)
                .orElseThrow(
                        () -> new CustomerNotFoundException("Customer could not find by id: " + id)
                );
    }
}
