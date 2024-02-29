package com.cts.customer_account_tracker.Exceptions;

public class AmountLimitReachedException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	public AmountLimitReachedException(String message) {
		super(message);
	}

	public AmountLimitReachedException() {

	}
}
