package com.cts.customer_account_tracker.Service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.customer_account_tracker.Entity.Customer;
import com.cts.customer_account_tracker.Exceptions.CustomerNotFoundException;
import com.cts.customer_account_tracker.Repository.CustomerRepository;
import com.cts.customer_account_tracker.Service.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerRepository customerRepo;

	public CustomerServiceImpl(CustomerRepository customerRepository) {
		this.customerRepo=customerRepository;
	}

	@Override
	public Customer addCustomer(Customer cust) {
		// Save the Customer using the customerRepo
		Customer c = customerRepo.save(cust);
		System.out.println("Customer is added Successfully!!!");
		return c;
	}

	@Override
	public List<Customer> getAllCustomers() throws CustomerNotFoundException {
		
		// Get the content (list of Customer objects) from the page
		List<Customer> customerList = customerRepo.findAll();
		
		// If the customerList is empty, throw a CustomerNotFoundException
		if (customerList.isEmpty()) {
			throw new CustomerNotFoundException("Customer List is Empty!");
		}
		
		return customerList;
	}

	@Override
	public Customer getCustomerById(Long customerId) throws CustomerNotFoundException {
		Customer customer;
		if (customerRepo.findById(customerId).isEmpty()) {
			// Throw a CustomerNotFoundException if the Customer with the given customerId is not found
			throw new CustomerNotFoundException();
		} else {
			// Retrieve the Customer by customerId using the customerRepo
			customer = customerRepo.findById(customerId).get();
		}

		return customer;
	}

	@Override
	public String deleteCustomerById(Long customerId) {
		// Delete the Customer by customerId using the customerRepo
		customerRepo.deleteById(customerId);
		System.out.println("Customer is deleted");
		return "Customer is Deleted";
	}

	@Override
	public Customer updateCustomer(Long customerId,Customer cust) {
		 // Update the Customer using the customerRepo
		Customer c=customerRepo.findById(customerId).orElseThrow(CustomerNotFoundException::new);
		c.setCustomerName(cust.getCustomerName());
		c.setEmail(cust.getEmail());
		c.setAddress(cust.getAddress());
		c.setContactNumber(cust.getContactNumber());
		c.setGender(cust.getGender());
		c.setAadharNo(cust.getAadharNo());
		Customer updatedCustomer=customerRepo.save(c);
		System.out.println("Customer Info Updated");
		return updatedCustomer;
	}

}
