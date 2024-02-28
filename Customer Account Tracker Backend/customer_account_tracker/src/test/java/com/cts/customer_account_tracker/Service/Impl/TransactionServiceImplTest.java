package com.cts.customer_account_tracker.Service.Impl;


import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.cts.customer_account_tracker.Entity.BankAccount;
import com.cts.customer_account_tracker.Entity.Transaction;
import com.cts.customer_account_tracker.Repository.BankAccountRepository;
import com.cts.customer_account_tracker.Repository.TransactionRepository;

class TransactionServiceImplTest {

    @Mock
    private BankAccountRepository bankAccountRepository;

    @Mock
    private TransactionRepository transactionRepository;

    @InjectMocks
    private TransactionServiceImpl transactionService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSendMoney() throws Exception {
        // Mock sender and receiver BankAccount objects
        BankAccount sender = new BankAccount();
        sender.setAccNum("senderAccNum");
        sender.setAccBal(1000.0);

        BankAccount receiver = new BankAccount();
        receiver.setAccNum("receiverAccNum");
        receiver.setAccBal(0.0);

        // Mock BankAccountRepository's findById method
        when(bankAccountRepository.findById("senderAccNum")).thenReturn(Optional.of(sender));
        when(bankAccountRepository.findById("receiverAccNum")).thenReturn(Optional.of(receiver));

        // Mock BankAccountRepository's save method
        when(bankAccountRepository.save(any(BankAccount.class))).thenReturn(null);

        // Mock TransactionRepository's save method
        when(transactionRepository.save(any(Transaction.class))).thenReturn(null);

        // Perform the sendMoney operation
        transactionService.sendMoney("senderAccNum", "receiverAccNum", 500.0);

        // Assert that the sender's balance is updated
        Assertions.assertEquals(500.0, sender.getAccBal());

        // Assert that the receiver's balance is updated
        Assertions.assertEquals(500.0, receiver.getAccBal());
    }

    @Test
    void testWithdrawMoney() throws Exception {
        // Mock user BankAccount object
        BankAccount user = new BankAccount();
        user.setAccNum("userAccNum");
        user.setAccBal(1000.0);

        // Mock BankAccountRepository's findById method
        when(bankAccountRepository.findById("userAccNum")).thenReturn(Optional.of(user));

        // Mock BankAccountRepository's save method
        when(bankAccountRepository.save(any(BankAccount.class))).thenReturn(null);

        // Mock TransactionRepository's save method
        when(transactionRepository.save(any(Transaction.class))).thenReturn(null);

        // Perform the withdrawMoney operation
        transactionService.withdrawMoney("userAccNum", 500.0);

        // Assert that the user's balance is updated
        Assertions.assertEquals(500.0, user.getAccBal());
    }

    @Test
    void testDepositMoney() throws Exception {
        // Mock user BankAccount object
        BankAccount user = new BankAccount();
        user.setAccNum("userAccNum");
        user.setAccBal(1000.0);

        // Mock BankAccountRepository's findById method
        when(bankAccountRepository.findById("userAccNum")).thenReturn(Optional.of(user));

        // Mock BankAccountRepository's save method
        when(bankAccountRepository.save(any(BankAccount.class))).thenReturn(null);

        // Mock TransactionRepository's save method
        when(transactionRepository.save(any(Transaction.class))).thenReturn(null);

        // Perform the depositMoney operation
        transactionService.depositMoney("userAccNum", 500.0);

        // Assert that the user's balance is updated
        Assertions.assertEquals(1500.0, user.getAccBal());
    }

    @Test
    void testGetTransactionHistory() throws Exception {
        // Mock user BankAccount object
        BankAccount user = new BankAccount();
        user.setAccNum("userAccNum");

        // Create transaction objects
        Transaction transaction1 = new Transaction();
        transaction1.setId(1L);
        transaction1.setUserAcc(user);
        transaction1.setTransactionType("Debited");
        transaction1.setAmount(500.0);
        transaction1.setTimestamp("01-01-2022 10:00:00");

        Transaction transaction2 = new Transaction();
        transaction2.setId(2L);
        transaction2.setUserAcc(user);
        transaction2.setTransactionType("Credited");
        transaction2.setAmount(1000.0);
        transaction2.setTimestamp("02-01-2022 11:00:00");

        List<Transaction> transactions = new ArrayList<>();
        transactions.add(transaction1);
        transactions.add(transaction2);

        user.setTransactions(transactions);

        // Mock BankAccountRepository's findById method
        when(bankAccountRepository.findById("userAccNum")).thenReturn(Optional.of(user));

        // Perform the getTransactionHistory operation
        List<Transaction> transactionHistory = transactionService.getTransactionHistory("userAccNum");

        // Assert that the transaction history is retrieved correctly
        Assertions.assertEquals(2, transactionHistory.size());
        Assertions.assertEquals(transaction1, transactionHistory.get(0));
        Assertions.assertEquals(transaction2, transactionHistory.get(1));
    }
}
