package com.cts.customer_account_tracker.Service;

import java.util.List;

import com.cts.customer_account_tracker.Entity.BankAccount;
import com.cts.customer_account_tracker.Exceptions.BankAccountNotFoundException;

public interface BankAccountService {

	public BankAccount addAccount(BankAccount acc);
	public List<BankAccount> getAllAccounts() throws BankAccountNotFoundException;
	public BankAccount getAccountById(String accNum) throws BankAccountNotFoundException;
	public String deleteAccountById(String accNum);
}
