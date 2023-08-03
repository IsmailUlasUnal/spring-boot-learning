package com.ismailulasunal.account.repository;

import com.ismailulasunal.account.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AccountRepository extends JpaRepository<Account, UUID> {

}
