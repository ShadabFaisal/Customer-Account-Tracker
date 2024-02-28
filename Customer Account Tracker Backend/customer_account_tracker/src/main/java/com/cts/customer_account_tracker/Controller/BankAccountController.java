package com.cts.customer_account_tracker.Controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cts.customer_account_tracker.Entity.BankAccount;
import com.cts.customer_account_tracker.Service.BankAccountService;

import jakarta.validation.Valid;

@CrossOrigin("*")
@RestController
@RequestMapping("/accounts")
public class BankAccountController {

	@Autowired
	private BankAccountService service;

	// Create a logger instance
	Logger logger = LoggerFactory.getLogger(BankAccountController.class);

	public BankAccountController(BankAccountService bankAccountService) {
		this.service=bankAccountService;
	}

	// Endpoint to add a new bank account
	@PostMapping("")
	public BankAccount addAccount(@Valid @RequestBody BankAccount a) {
		// Call the service to add the account
		BankAccount acc = service.addAccount(a);

		// Log an information message
		logger.info("Account created for a customer...");

		// Return the created account
		return acc;
	}

	// Endpoint to retrieve all bank accounts
	@GetMapping("")
	public List<BankAccount> getAllAccounts() {
		// Call the service to get all accounts
		List<BankAccount> listOfBankAcc = service.getAllAccounts();

		// Log an information message
		logger.info("Retrieved the list of BankAccounts...");

		// Return the list of accounts
		return listOfBankAcc;
	}

	// Endpoint to retrieve a bank account by account number
	@GetMapping("/{accNum}")
	public BankAccount getAccountById(@PathVariable("accNum") String accNum) {
		// Call the service to get the account by account number
		BankAccount b1 = service.getAccountById(accNum);

		// Log an information message
		logger.info("Retrieved Account details for a customer...");

		// Return the account
		return b1;
	}

	// Endpoint to delete a bank account by account number
	@DeleteMapping("/{accNum}")
	public void deleteAccount(@PathVariable("accNum") String accNum) {
		// Call the service to delete the account by account number
		service.deleteAccountById(accNum);

		// Log an information message
		logger.info("Account deleted...");
	}


}
