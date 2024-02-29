package com.cts.customer_account_tracker.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

@Entity
public class Transaction {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "accNum")
	private BankAccount userAcc;

	@NotNull(message = "Select the Transaction Type...")
	private String transactionType;

	@NotNull(message = "Enter the amount...")
	private double amount;

	@NotEmpty
	private String timestamp;

	public Transaction() {

	}

	public Transaction(Long id, BankAccount userAcc, String transactionType, double amount, String timestamp) {
		super();
		this.id = id;
		this.userAcc = userAcc;
		this.transactionType = transactionType;
		this.amount = amount;
		this.timestamp = timestamp;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public BankAccount getUserAcc() {
		return userAcc;
	}

	public void setUserAcc(BankAccount userAcc) {
		this.userAcc = userAcc;
	}

	public String getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	@Override
	public String toString() {
		return "Transaction [id=" + id + ", userAcc=" + userAcc + ", transactionType=" + transactionType + ", amount="
				+ amount + ", timestamp=" + timestamp + "]";
	}

}
