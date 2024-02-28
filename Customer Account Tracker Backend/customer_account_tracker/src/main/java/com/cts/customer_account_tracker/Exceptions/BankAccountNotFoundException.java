package com.cts.customer_account_tracker.Exceptions;

public class BankAccountNotFoundException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	public BankAccountNotFoundException(String message) {
        super(message);
    }
	public BankAccountNotFoundException() {
		
	}

}
