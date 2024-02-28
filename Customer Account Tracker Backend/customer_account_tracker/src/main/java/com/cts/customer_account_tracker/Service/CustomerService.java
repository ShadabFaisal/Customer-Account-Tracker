package com.cts.customer_account_tracker.Service;

import java.util.List;

import com.cts.customer_account_tracker.Entity.Customer;
import com.cts.customer_account_tracker.Exceptions.CustomerNotFoundException;

public interface CustomerService {

	public Customer addCustomer(Customer cust);
	public List<Customer> getAllCustomers() throws CustomerNotFoundException;
	public Customer getCustomerById(Long customerId) throws CustomerNotFoundException;
	public String deleteCustomerById(Long customerId);
	public Customer updateCustomer(Long id,Customer cust);
}
