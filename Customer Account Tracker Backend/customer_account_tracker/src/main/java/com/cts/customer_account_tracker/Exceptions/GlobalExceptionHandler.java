package com.cts.customer_account_tracker.Exceptions;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @Value(value = "${data.exception.message1}")
    private String message1;
    @Value(value = "${data.exception.message2}")
    private String message2;
    @Value(value = "${data.exception.message3}")
    private String message3;
    @Value(value = "${data.exception.message4}")
    private String message4;
    @Value(value = "${data.exception.message5}")
    private String message5;
    @Value(value = "${data.exception.message6}")
    private String message6;
    
    @ExceptionHandler(value = CustomerNotFoundException.class)
    public ResponseEntity<String> customerNotFoundException(CustomerNotFoundException customerNotFoundException) {
        return new ResponseEntity<String>(message1, HttpStatus.NOT_FOUND);
    }
   
    @ExceptionHandler(value = BankAccountNotFoundException.class)
    public ResponseEntity<String> bankAccountNotFoundException(BankAccountNotFoundException bankAccountNotFoundException) {
        return new ResponseEntity<String>(message2, HttpStatus.NOT_FOUND);
    }
    
    @ExceptionHandler(value = InsufficientBalanceException.class)
    public ResponseEntity<String> insufficientBalanceException(InsufficientBalanceException insufficientBalanceException) {
        return new ResponseEntity<String>(message3, HttpStatus.BAD_REQUEST);
    }
    
    @ExceptionHandler(value = UserNotFoundException.class)
    public ResponseEntity<String> userNotFoundException(UserNotFoundException userNotFoundException) {
        return new ResponseEntity<String>(message4, HttpStatus.NOT_FOUND);
    }
    
    @ExceptionHandler(value = AmountLimitReachedException.class)
    public ResponseEntity<String> amountLimitReachedException(AmountLimitReachedException amountLimitReachedException) {
        return new ResponseEntity<String>(message5, HttpStatus.FORBIDDEN);
    }
    
    @ExceptionHandler(value = WeakPasswordException.class)
    public ResponseEntity<String> weakPasswordException(WeakPasswordException weakPasswordException) {
        return new ResponseEntity<String>(message6, HttpStatus.BAD_REQUEST);
    }
    
    
    
   
}