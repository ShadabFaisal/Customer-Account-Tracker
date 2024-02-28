package com.cts.customer_account_tracker.Exceptions;

public class WeakPasswordException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public WeakPasswordException(String message) {
		super(message);
	}
	
	public WeakPasswordException() {
		
	}

}
