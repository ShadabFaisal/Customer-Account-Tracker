package com.cts.customer_account_tracker.Controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.cts.customer_account_tracker.Entity.BankAccount;
import com.cts.customer_account_tracker.Entity.Customer;
import com.cts.customer_account_tracker.Entity.Transaction;
import com.cts.customer_account_tracker.Exceptions.BankAccountNotFoundException;
import com.cts.customer_account_tracker.Exceptions.InsufficientBalanceException;
import com.cts.customer_account_tracker.Service.TransactionService;

class TransactionControllerTest {

	@Mock
	private TransactionService transactionService;

	@InjectMocks
	private TransactionController transactionController;

	@BeforeEach
	void setup() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void testSendMoney() throws BankAccountNotFoundException, InsufficientBalanceException {
		String senderAccNum = "123456789";
		String receiverAccNum = "987654321";
		double amount = 100.0;

		ResponseEntity<String> expectedResponse = ResponseEntity.ok("Rs. " + amount + " sent successfully from Sender Account " + senderAccNum
				+ " to Receiver Bank Account " + receiverAccNum);

		doNothing().when(transactionService).sendMoney(eq(senderAccNum), eq(receiverAccNum), eq(amount));

		ResponseEntity<String> response = transactionController.sendMoney(senderAccNum, receiverAccNum, amount);

		assertEquals(expectedResponse.getStatusCode(), response.getStatusCode());
		assertEquals(expectedResponse.getBody(), response.getBody());
		verify(transactionService, times(1)).sendMoney(anyString(), anyString(), anyDouble());
	}

	@Test
	void testWithdrawMoney() throws BankAccountNotFoundException, InsufficientBalanceException {
		String userAccNum = "123456789";
		double amount = 100.0;

		ResponseEntity<String> expectedResponse = ResponseEntity.ok("Rs." + amount + " withdrawn successfully from Account Number: "+userAccNum);

		doNothing().when(transactionService).withdrawMoney(eq(userAccNum), eq(amount));

		ResponseEntity<String> response = transactionController.withdrawMoney(userAccNum, amount);

		assertEquals(expectedResponse.getStatusCode(), response.getStatusCode());
		assertEquals(expectedResponse.getBody(), response.getBody());
		verify(transactionService, times(1)).withdrawMoney(anyString(), anyDouble());
	}

	@Test
	void testDepositMoney() throws BankAccountNotFoundException {
		String userAccNum = "123456789";
		double amount = 100.0;

		ResponseEntity<String> expectedResponse = ResponseEntity.ok("Rs." + amount + " deposited successfully into Account Number: "+userAccNum);

		doNothing().when(transactionService).depositMoney(eq(userAccNum), eq(amount));

		ResponseEntity<String> response = transactionController.depositMoney(userAccNum, amount);

		assertEquals(expectedResponse.getStatusCode(), response.getStatusCode());
		assertEquals(expectedResponse.getBody(), response.getBody());
		verify(transactionService, times(1)).depositMoney(anyString(), anyDouble());
	}

	@Test
	void testGetTransactionHistory() throws BankAccountNotFoundException {
	    String userAccNum = "123456789";

	    List<Transaction> expectedTransactionHistory = Arrays.asList(
	        new Transaction(1L, new BankAccount("7872819372183798", "Savings", 600, "07-06-23", new Customer(1L, "Manish Kumar",
					"manish@gmail.com", "Jamshedpur", "7277394566", "Male", "343409876789")), "Credited", 100.0, "2023-06-08 10:00:00"),
	        new Transaction(2L, new BankAccount("4512281937283798", "Savings", 6200, "07-06-23", new Customer(2L, "Venkatesh Naidu",
					"manish@gmail.com", "Jamshedpur", "7277394566", "Male", "388978947382")), "Fund Transfer", 200.0, "2023-06-07 15:30:00")
	    );

	    when(transactionService.getTransactionHistory(eq(userAccNum))).thenReturn(expectedTransactionHistory);

	    ResponseEntity<List<Transaction>> response = transactionController.getTransactionHistory(userAccNum);

	    assertEquals(HttpStatus.OK, response.getStatusCode());
	    assertEquals(expectedTransactionHistory, response.getBody());
	    verify(transactionService, times(1)).getTransactionHistory(eq(userAccNum));
	}

}
