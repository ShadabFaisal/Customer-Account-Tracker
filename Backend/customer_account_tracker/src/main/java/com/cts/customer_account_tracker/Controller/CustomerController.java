package com.cts.customer_account_tracker.Controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cts.customer_account_tracker.Entity.Customer;
import com.cts.customer_account_tracker.Service.CustomerService;

import jakarta.validation.Valid;

@CrossOrigin("*")
@RestController
@RequestMapping("/customers")
public class CustomerController {

	@Autowired
	private CustomerService custService;

	// Create a logger instance
	Logger logger = LoggerFactory.getLogger(CustomerController.class);

	// Endpoint to display a welcome message
	@GetMapping("/homepage")
	public String hello() {
		// Log an information message
		logger.info("Application started at http://localhost:8080");

		// Return a welcome message
		return "Welcome to Customer Account Tracker!!!";
	}

	// Endpoint to add a new customer
	@PostMapping("")
	public Customer addCustomer(@Valid @RequestBody Customer cust) {
		// Log an information message
		logger.info("Customer added to the Database...");

		// Call the service to add the customer
		Customer c = custService.addCustomer(cust);

		// Return the added customer
		return c;
	}

	// Endpoint to retrieve all customers with pagination support
	@GetMapping("")
	public List<Customer> getAllCustomers() {
		// Log an information message
		logger.info("Retrieved all customers...");

		// Call the service to get all customers with pagination
		return custService.getAllCustomers();
	}

	// Endpoint to retrieve a customer by customer ID
	@GetMapping("/{customerId}")
	public Customer getCustomerById(@PathVariable("customerId") Long customerId) {
		// Log an information message
		logger.info("Retrieved customer details...");

		// Call the service to get the customer by ID
		return custService.getCustomerById(customerId);
	}

	// Endpoint to delete a customer by customer ID
	@DeleteMapping("/{customerId}")
	public String deleteCustomer(@PathVariable("customerId") Long customerId) {
		// Call the service to delete the customer by ID
		custService.deleteCustomerById(customerId);

		// Log an information message
		logger.info("Customer deleted...");
		return "Customer is Deleted";
	}

	// Endpoint to update a customer's information
	@PutMapping("/{customerId}")
	public Customer updateCustomer(@PathVariable("customerId") Long customerId, @Valid @RequestBody Customer c) {
		// Call the service to update the customer
		Customer cust = custService.updateCustomer(customerId, c);

		// Log an information message
		logger.info("Customer Info Updated...");

		// Return the updated customer
		return cust;
	}
}
