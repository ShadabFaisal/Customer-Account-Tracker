package com.cts.customer_account_tracker.Controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cts.customer_account_tracker.Entity.Transaction;
import com.cts.customer_account_tracker.Exceptions.BankAccountNotFoundException;
import com.cts.customer_account_tracker.Exceptions.InsufficientBalanceException;
import com.cts.customer_account_tracker.Service.TransactionService;

@CrossOrigin("*")
@RestController
@RequestMapping("/user")
public class TransactionController {

	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private TransactionService transactionService;

	// Endpoint to send money from one account to another
	@PostMapping("/{senderAccNum}/send-money/{receiverAccNum}")
	public ResponseEntity<String> sendMoney(@PathVariable String senderAccNum, @PathVariable String receiverAccNum,
			@RequestParam double amount) {
		try {
			// Call the transaction service to send money
			transactionService.sendMoney(senderAccNum, receiverAccNum, amount);

			// Log an information message
			logger.info("Money sent from Account Number: " + senderAccNum + " to Account Number " + receiverAccNum);

			// Return a success response
			return ResponseEntity.ok("Rs. " + amount + " sent successfully from Sender Account " + senderAccNum
					+ " to Receiver Bank Account " + receiverAccNum);
		} catch (BankAccountNotFoundException e) {
			// Return a bad request response for invalid account number
			return ResponseEntity.badRequest().body("Invalid Account Number");
		} catch (InsufficientBalanceException e) {
			// Return a bad request response for insufficient balance
			return ResponseEntity.ok("Insufficient Balance. Add Money to your Account...");
		}
	}

	// Endpoint to withdraw money from an account
	@PostMapping("/{userAccNum}/withdraw-money")
	public ResponseEntity<String> withdrawMoney(@PathVariable String userAccNum, @RequestParam double amount) {
		try {
			// Call the transaction service to withdraw money
			transactionService.withdrawMoney(userAccNum, amount);

			// Log an information message
			logger.info("Money debited from Account Number: " + userAccNum);

			// Return a success response
			return ResponseEntity.ok("Rs." + amount + " withdrawn successfully from Account Number: "+userAccNum);
		} catch (BankAccountNotFoundException e) {
			// Return a bad request response for invalid account number
			return ResponseEntity.badRequest().body("Invalid Account Number");
		} catch (InsufficientBalanceException e) {
			// Return a bad request response for insufficient balance
			return ResponseEntity.ok("Insufficient Balance. Add Money to your Account...");
		}
	}

	// Endpoint to deposit money into an account
	@PostMapping("/{userAccNum}/deposit-money")
	public ResponseEntity<String> depositMoney(@PathVariable String userAccNum, @RequestParam double amount) {
		try {
			// Call the transaction service to deposit money
			transactionService.depositMoney(userAccNum, amount);

			// Log an information message
			logger.info("Money Credited from Account Number: " + userAccNum);

			// Return a success response
			return ResponseEntity.ok("Rs." + amount + " deposited successfully into Account Number: "+userAccNum);
		} catch (BankAccountNotFoundException e) {
			// Return a bad request response for invalid account number
			return ResponseEntity.badRequest().body("Invalid Account Number");
		}
	}

	// Endpoint to retrieve transaction history for an account
	@GetMapping("/{userAccNum}/transactions")
	public ResponseEntity<List<Transaction>> getTransactionHistory(@PathVariable String userAccNum) {
		try {
			// Call the transaction service to retrieve transaction history
			List<Transaction> transactionHistory = transactionService.getTransactionHistory(userAccNum);

			// Log an information message
			logger.info("Transaction History for a user");

			// Return the transaction history
			return ResponseEntity.ok(transactionHistory);
		} catch (BankAccountNotFoundException e) {
			// Return a bad request response for invalid account number
			return ResponseEntity.badRequest().body(null);
		}
	}
	
	@DeleteMapping("/delete-transaction/{id}")
	public String deleteTransaction(@PathVariable("id") Long id) {
		transactionService.deleteTransactions(id);
		return "Transaction Deleted";
	}
	
}
