package com.cts.customer_account_tracker.Service;

import java.util.List;

import com.cts.customer_account_tracker.Entity.BankAccount;
import com.cts.customer_account_tracker.Entity.Transaction;

public interface TransactionService {

	public void sendMoney(String senderAccNum, String receiverAccNum, double amount);
	public void withdrawMoney(String userAccNum, double amount);
	public void depositMoney(String userAccNum, double amount);
	public Transaction createTransaction(BankAccount acc, String transactionType, double amount);
	public List<Transaction> getTransactionHistory(String userAccNum);
	public String deleteTransactions(Long id);
}
