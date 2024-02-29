package com.cts.customer_account_tracker.Service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.customer_account_tracker.Entity.BankAccount;
import com.cts.customer_account_tracker.Exceptions.BankAccountNotFoundException;
import com.cts.customer_account_tracker.Repository.BankAccountRepository;
import com.cts.customer_account_tracker.Service.BankAccountService;

@Service
public class BankAccountServiceImpl implements BankAccountService {

	@Autowired
	private BankAccountRepository bankRepository;

	public BankAccountServiceImpl(BankAccountRepository accRepository) {
		this.bankRepository=accRepository;
	}

	@Override
	public BankAccount addAccount(BankAccount acc) {
		// Save the BankAccount using the bankRepository
		BankAccount ac = bankRepository.save(acc);
		System.out.println("BankAccount is added to the database");
		return ac;
	}

	@Override
	public List<BankAccount> getAllAccounts() throws BankAccountNotFoundException {
		// Retrieve all BankAccounts using the bankRepository
		List<BankAccount> listOfAccounts = (List<BankAccount>) bankRepository.findAll();
		return listOfAccounts;
	}

	@Override
	public BankAccount getAccountById(String accNum) throws BankAccountNotFoundException {
		BankAccount acc;
		if (bankRepository.findById(accNum).isEmpty()) {
			// Throw an exception if the BankAccount with the given accNum is not found
			throw new BankAccountNotFoundException();
		} else {
			// Retrieve the BankAccount by accNum using the bankRepository
			acc = bankRepository.findById(accNum).get();
		}
		System.out.println(acc);
		return acc;
	}

	@Override
	public String deleteAccountById(String accNum) {
		// Delete the BankAccount by accNum using the bankRepository
		bankRepository.deleteById(accNum);
		return "Account is deleted";
		
	}


}
