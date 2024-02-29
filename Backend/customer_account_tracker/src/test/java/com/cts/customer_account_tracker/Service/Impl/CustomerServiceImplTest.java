package com.cts.customer_account_tracker.Service.Impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
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

import com.cts.customer_account_tracker.Entity.Customer;
import com.cts.customer_account_tracker.Repository.CustomerRepository;
import com.cts.customer_account_tracker.Service.CustomerService;

class CustomerServiceImplTest {

	@Mock
	private CustomerRepository customerRepository;
	private CustomerService customerService;
	AutoCloseable autoCloseable;
	Customer customer;
	Customer customer2;

	@BeforeEach
	void setUp() {
		autoCloseable = MockitoAnnotations.openMocks(this);
		customerService = new CustomerServiceImpl(customerRepository);
		customer = new Customer(1L, "Suresh Raina", "suresh@gmail.com", "Kolkata", "6277390876", "Male",
				"123409876789");
		customer2 = new Customer(2L, "Manish Kumar", "manish@gmail.com", "Jamshedpur", "7277394566", "Male",
				"343409876789");
	}

	@AfterEach
	void tearDown() throws Exception {
		autoCloseable.close();
	}

	@Test
	void testAddCustomer() {
		mock(Customer.class);
		mock(CustomerRepository.class);

		when(customerRepository.save(customer)).thenReturn(customer);
		when(customerRepository.save(customer2)).thenReturn(customer2);

		assertThat(customerService.addCustomer(customer)).isEqualTo(customer);
		assertThat(customerService.addCustomer(customer2)).isEqualTo(customer2);
	}

	@Test
	void testGetAllCustomers() {
		mock(Customer.class);
		mock(CustomerRepository.class);

		when(customerRepository.findAll()).thenReturn(new ArrayList<Customer>(Collections.singleton(customer)));
		
		assertThat(customerService.getAllCustomers().get(0).getAadharNo()).isEqualTo(customer.getAadharNo());
	}

	@Test
	void testGetCustomerById() {
		mock(Customer.class);
		mock(CustomerRepository.class);

		when(customerRepository.findById(1L)).thenReturn(Optional.ofNullable(customer));

		assertThat(customerService.getCustomerById(1L).getCustomerName()).isEqualTo(customer.getCustomerName());
	}

	@Test
	void testDeleteCustomerById() {
		mock(Customer.class);
		mock(CustomerRepository.class,Mockito.CALLS_REAL_METHODS);
		
		doAnswer(Answers.CALLS_REAL_METHODS).when(customerRepository).deleteById(any());
		
		assertThat(customerService.deleteCustomerById(1L)).isEqualTo("Customer is Deleted");	
	}
	
	@Test
    public void testUpdateCustomer() {
        Long customerId = 1L;
        Customer existingCustomer = new Customer();
        existingCustomer.setCustomerId(customerId);
        existingCustomer.setCustomerName("John Doe");
        existingCustomer.setEmail("john.doe@example.com");
        existingCustomer.setAddress("123 Main St");
        existingCustomer.setContactNumber("1234567890");
        existingCustomer.setGender("Male");
        existingCustomer.setAadharNo("123456789");

        Customer updatedCustomer = new Customer();
        updatedCustomer.setCustomerId(customerId);
        updatedCustomer.setCustomerName("Jane Smith");
        updatedCustomer.setEmail("jane.smith@example.com");
        updatedCustomer.setAddress("456 Elm St");
        updatedCustomer.setContactNumber("9876543210");
        updatedCustomer.setGender("Female");
        updatedCustomer.setAadharNo("987654321");

        when(customerRepository.findById(customerId)).thenReturn(Optional.of(existingCustomer));
        when(customerRepository.save(any(Customer.class))).thenReturn(updatedCustomer);

        Customer result = customerService.updateCustomer(customerId, updatedCustomer);

        assertNotNull(result);
        assertEquals(customerId, result.getCustomerId());
        assertEquals(updatedCustomer.getCustomerName(), result.getCustomerName());
        assertEquals(updatedCustomer.getEmail(), result.getEmail());
        assertEquals(updatedCustomer.getAddress(), result.getAddress());
        assertEquals(updatedCustomer.getContactNumber(), result.getContactNumber());
        assertEquals(updatedCustomer.getGender(), result.getGender());
        assertEquals(updatedCustomer.getAadharNo(), result.getAadharNo());

        verify(customerRepository, times(1)).findById(customerId);
        verify(customerRepository, times(1)).save(any(Customer.class));
    }


}
