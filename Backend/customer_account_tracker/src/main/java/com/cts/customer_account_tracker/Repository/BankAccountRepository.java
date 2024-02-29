package com.cts.customer_account_tracker.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cts.customer_account_tracker.Entity.BankAccount;

import jakarta.transaction.Transactional;

@Repository
@Transactional
public interface BankAccountRepository extends JpaRepository<BankAccount, String> {

}
