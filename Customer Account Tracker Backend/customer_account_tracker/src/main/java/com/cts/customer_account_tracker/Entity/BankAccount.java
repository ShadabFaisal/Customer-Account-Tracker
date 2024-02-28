package com.cts.customer_account_tracker.Entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotNull;

@Entity
public class BankAccount {

	@Id
	private String accNum;

	@NotNull(message = "Provide the Account Type !")
	private String accType;

	@NotNull
	private double accBal;

	@NotNull(message = "Provide the Account creation Date !")
	private String accDate;

	@OneToOne
	@JoinColumn(name = "customerId")
	private Customer cust;

	@OneToMany(mappedBy = "userAcc")
	private List<Transaction> transactions = new ArrayList<>();

	public BankAccount() {
		this.accNum = generateAccountNumber();
	}

	public String getAccNum() {
		return accNum;
	}

	public void setAccNum(String accNum) {
		this.accNum = accNum;
	}

	public BankAccount(String accNum, String accType, double accBal, String accDate, Customer cust) {
		super();
		this.accNum = accNum;
		this.accType = accType;
		this.accBal = accBal;
		this.accDate = accDate;
		this.cust = cust;
	}

	public String getAccType() {
		return accType;
	}

	public void setAccType(String accType) {
		this.accType = accType;
	}

	public double getAccBal() {
		return accBal;
	}

	public void setAccBal(double accBal) {
		this.accBal = accBal;
	}

	public String getAccDate() {
		return accDate;
	}

	public void setAccDate(String accDate) {
		this.accDate = accDate;
	}

	public Customer getCust() {
		return cust;
	}

	public void setCust(Customer cust) {
		this.cust = cust;
	}

	public List<Transaction> getTransactions() {
		return transactions;
	}

	public void setTransactions(List<Transaction> transactions) {
		this.transactions = transactions;
	}
	


	private String generateAccountNumber() {
		Random random = new Random();
		long accountNumber = random.nextLong() % 9000000000000000L + 1000000000000000L; // Generates a random 16-digit
																						// number
		String accN = String.valueOf(accountNumber);
		String accNum = "";
		if (accN.charAt(0) == '-') {
			accNum += accN.substring(1);
		}
		return accNum;
	}

}
