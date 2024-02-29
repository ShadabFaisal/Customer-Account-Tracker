package com.cts.customer_account_tracker.Service.Impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Answers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.cts.customer_account_tracker.Entity.BankAccount;
import com.cts.customer_account_tracker.Entity.Customer;
import com.cts.customer_account_tracker.Repository.BankAccountRepository;
import com.cts.customer_account_tracker.Repository.CustomerRepository;
import com.cts.customer_account_tracker.Service.BankAccountService;

class BankAccountServiceImplTest {

	@Mock
	private BankAccountRepository accRepository;
	private BankAccountService accService;
	AutoCloseable autoCloseable;
	BankAccount acc;
	Customer c;

	@BeforeEach
	void setUp() throws Exception {
		autoCloseable = MockitoAnnotations.openMocks(this);
		accService = new BankAccountServiceImpl(accRepository);
		acc = new BankAccount("7872819372183798", "Savings", 600, "07-06-23", new Customer(1L, "Manish Kumar",
				"manish@gmail.com", "Jamshedpur", "7277394566", "Male", "343409876789"));
	}

	@AfterEach
	void tearDown() throws Exception {
		autoCloseable.close();
	}

	@Test
	void testAddAccount() {
		mock(Customer.class);
		mock(BankAccount.class);
		mock(BankAccountRepository.class);

		when(accRepository.save(acc)).thenReturn(acc);

		assertThat(accService.addAccount(acc)).isEqualTo(acc);
	}

	@Test
	void testGetAllAccounts() {
		mock(Customer.class);
		mock(CustomerRepository.class);

		when(accRepository.findAll()).thenReturn(new ArrayList<BankAccount>(Collections.singleton(acc)));
		
		assertThat(accService.getAllAccounts().get(0).getAccNum()).isEqualTo(acc.getAccNum());
	}

	@Test
	void testGetAccountById() {
		mock(Customer.class);
		mock(CustomerRepository.class);

		when(accRepository.findById("7872819372183798")).thenReturn(Optional.ofNullable(acc));

		assertThat(accService.getAccountById("7872819372183798").getCust().getCustomerName()).isEqualTo(acc.getCust().getCustomerName());
	}

	@Test
	void testDeleteAccountById() {
		mock(Customer.class);
		mock(CustomerRepository.class,Mockito.CALLS_REAL_METHODS);
		
		doAnswer(Answers.CALLS_REAL_METHODS).when(accRepository).deleteById(any());
		
		assertThat(accService.deleteAccountById("7872819372183798")).isEqualTo("Account is deleted");
	}

}
