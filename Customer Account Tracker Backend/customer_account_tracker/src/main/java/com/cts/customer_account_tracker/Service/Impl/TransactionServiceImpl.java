package com.cts.customer_account_tracker.Service.Impl;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.customer_account_tracker.Entity.BankAccount;
import com.cts.customer_account_tracker.Entity.Transaction;
import com.cts.customer_account_tracker.Exceptions.AmountLimitReachedException;
import com.cts.customer_account_tracker.Exceptions.BankAccountNotFoundException;
import com.cts.customer_account_tracker.Exceptions.InsufficientBalanceException;
import com.cts.customer_account_tracker.Repository.BankAccountRepository;
import com.cts.customer_account_tracker.Repository.TransactionRepository;
import com.cts.customer_account_tracker.Service.TransactionService;

@Service
public class TransactionServiceImpl implements TransactionService {

	@Autowired
	private BankAccountRepository bankRepository;

	@Autowired
	private TransactionRepository transactionRepository;



	@Override
	public void sendMoney(String senderAccNum, String receiverAccNum, double amount)
			throws AmountLimitReachedException, BankAccountNotFoundException, InsufficientBalanceException {
		// Retrieve the sender and receiver BankAccount objects from the bankRepository
		// based on the account numbers
		BankAccount sender = bankRepository.findById(senderAccNum).orElseThrow(BankAccountNotFoundException::new);
		BankAccount receiver = bankRepository.findById(receiverAccNum).orElseThrow(BankAccountNotFoundException::new);

		if (amount > 5000000) {
			throw new AmountLimitReachedException();
		}
		if(sender.getAccBal()==0 || sender.getAccBal()<amount) {
			throw new InsufficientBalanceException();
			
		}
		// Check if the sender has sufficient balance
		if (sender.getAccBal() >= amount) {
			// Update the account balances of the sender and receiver
			sender.setAccBal(sender.getAccBal() - amount);
			receiver.setAccBal(receiver.getAccBal() + amount);

			// Create transaction objects for the sender and receiver
			Transaction senderTransaction = createTransaction(sender, "Fund Transfer", -amount);
			Transaction receiverTransaction = createTransaction(receiver, "Credited", amount);

			// Save the updated sender and receiver BankAccount objects and the transaction
			// objects
			bankRepository.save(sender);
			bankRepository.save(receiver);
			transactionRepository.save(senderTransaction);
			transactionRepository.save(receiverTransaction);
		} 
	}

	@Override
	public void withdrawMoney(String userAccNum, double amount)
			throws AmountLimitReachedException, BankAccountNotFoundException, InsufficientBalanceException {
		// Retrieve the BankAccount object of the user based on the account number
		BankAccount userAcc = bankRepository.findById(userAccNum).orElseThrow(BankAccountNotFoundException::new);

		if (amount > 5000000) {
			throw new AmountLimitReachedException();
		}
		if(userAcc.getAccBal()==0 || userAcc.getAccBal()<amount) {
			throw new InsufficientBalanceException();
			
		}
		// Check if the user has sufficient balance
		if (userAcc.getAccBal() >= amount) {
			// Update the account balance of the user
			userAcc.setAccBal(userAcc.getAccBal() - amount);

			// Create a transaction object for the withdrawal
			Transaction transaction = createTransaction(userAcc, "Debited", -amount);

			// Save the updated BankAccount object and the transaction object
			bankRepository.save(userAcc);
			transactionRepository.save(transaction);
		}
	}

	@Override
	public void depositMoney(String userAccNum, double amount)
			throws AmountLimitReachedException, BankAccountNotFoundException, InsufficientBalanceException {
		// Retrieve the BankAccount object of the user based on the account number
		BankAccount userAcc = bankRepository.findById(userAccNum).orElseThrow(BankAccountNotFoundException::new);

		if (amount >= 5000000) {
			throw new AmountLimitReachedException();
		}

		// Update the account balance of the user
		userAcc.setAccBal(userAcc.getAccBal() + amount);

		// Create a transaction object for the deposit
		Transaction transaction = createTransaction(userAcc, "Credited", amount);

		// Save the updated BankAccount object and the transaction object
		bankRepository.save(userAcc);
		transactionRepository.save(transaction);
	}

	@Override
	public List<Transaction> getTransactionHistory(String userAccNum) {
		// Retrieve the BankAccount object of the user based on the account number
		BankAccount userAcc = bankRepository.findById(userAccNum).orElseThrow(BankAccountNotFoundException::new);

		// Return the list of transactions associated with the user's account
		return userAcc.getTransactions();
	}

	@Override
	public Transaction createTransaction(BankAccount acc, String transactionType, double amount) {
		// Create a new Transaction object
		Transaction transaction = new Transaction();

		// Set the BankAccount, transaction type, and amount for the transaction
		transaction.setUserAcc(acc);
		transaction.setTransactionType(transactionType);
		transaction.setAmount(amount);

		// Set the timestamp for the transaction
		LocalDateTime timestamp = LocalDateTime.now();
		DateTimeFormatter formattedTimestamp = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
		String formattedDate = timestamp.format(formattedTimestamp);
		transaction.setTimestamp(formattedDate);

		return transaction;
	}

	@Override
	public String deleteTransactions(Long id) {
		transactionRepository.deleteById(id);
		System.out.println("Transaction is Deleted!!");
		return "Transaction is Deleted!!";
	}

}
