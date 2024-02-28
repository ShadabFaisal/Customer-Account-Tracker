package com.cts.customer_account_tracker.Exceptions;

public class InsufficientBalanceException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	public InsufficientBalanceException(String message) {
		super(message);
	}
	public InsufficientBalanceException() {
		
	}
	
	
}