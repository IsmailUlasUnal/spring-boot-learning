package com.ismailulasunal.account;

import com.ismailulasunal.account.model.Account;
import com.ismailulasunal.account.model.Customer;
import com.ismailulasunal.account.repository.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.math.BigDecimal;
import java.util.HashSet;

@SpringBootApplication
public class AccountApplication implements CommandLineRunner {

	private CustomerRepository customerRepository;

	public AccountApplication(CustomerRepository customerRepository) {
		this.customerRepository = customerRepository;
	}

	public static void main(String[] args) {
		SpringApplication.run(AccountApplication.class, args);
	}


	@Override
	public void run(String... args) throws Exception {
		Customer customer = customerRepository.save(new Customer("Ulas", "Unal", new HashSet<>()));
		System.out.println(customer);
	}
}
