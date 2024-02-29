package com.cts.customer_account_tracker.Controller;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.cts.customer_account_tracker.Entity.Customer;
import com.cts.customer_account_tracker.Service.CustomerService;

public class CustomerControllerTest {

	private MockMvc mockMvc;

	@Mock
	private CustomerService customerService;

	@InjectMocks
	private CustomerController customerController;

	@BeforeEach
	public void setup() {
		MockitoAnnotations.openMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(customerController).build();
	}

	@Test
	public void testHello() throws Exception {
		mockMvc.perform(get("/customers/homepage")).andExpect(status().isOk())
				.andExpect(content().string(is("Welcome to Customer Account Tracker!!!")));
	}

	@Test
	public void testAddCustomer() throws Exception {
		Customer customer = new Customer();
		customer.setCustomerId(1L);
		customer.setCustomerName("Suresh Raina");
		customer.setEmail("sureshraina@example.com");
		customer.setAddress("123 Main St");
		customer.setContactNumber("1234567890");
		customer.setGender("Male");
		customer.setAadharNo("123456789012");

		when(customerService.addCustomer(any(Customer.class))).thenReturn(customer);

		mockMvc.perform(post("/customers").contentType("application/json").content(
				"{\"customerId\":1,\"customerName\":\"Suresh Raina\",\"email\":\"sureshraina@example.com\",\"address\":\"123 Main St\",\"contactNumber\":\"1234567890\",\"gender\":\"Male\",\"aadharNo\":\"123456789012\"}"))
				.andExpect(status().isOk()).andExpect(jsonPath("$.customerId", is(1)))
				.andExpect(jsonPath("$.customerName", is("Suresh Raina")))
				.andExpect(jsonPath("$.email", is("sureshraina@example.com")))
				.andExpect(jsonPath("$.address", is("123 Main St")))
				.andExpect(jsonPath("$.contactNumber", is("1234567890"))).andExpect(jsonPath("$.gender", is("Male")))
				.andExpect(jsonPath("$.aadharNo", is("123456789012")));
	}

	@Test
	public void testGetAllCustomers() throws Exception {
		List<Customer> customers = new ArrayList<>();
		customers.add(new Customer(1L, "Suresh Raina", "sureshraina@example.com", "123 Main St", "1234567890", "Male",
				"123456789012"));
		customers.add(new Customer(2L, "Virat Kohli", "viratkohli@example.com", "456 Elm St", "9876543210", "Female",
				"987654321098"));

		when(customerService.getAllCustomers()).thenReturn(customers);

		mockMvc.perform(get("/customers")).andExpect(status().isOk()).andExpect(jsonPath("$[0].customerId", is(1)))
				.andExpect(jsonPath("$[0].customerName", is("Suresh Raina")))
				.andExpect(jsonPath("$[0].email", is("sureshraina@example.com")))
				.andExpect(jsonPath("$[0].address", is("123 Main St")))
				.andExpect(jsonPath("$[0].contactNumber", is("1234567890")))
				.andExpect(jsonPath("$[0].gender", is("Male"))).andExpect(jsonPath("$[0].aadharNo", is("123456789012")))
				.andExpect(jsonPath("$[1].customerId", is(2)))
				.andExpect(jsonPath("$[1].customerName", is("Virat Kohli")))
				.andExpect(jsonPath("$[1].email", is("viratkohli@example.com")))
				.andExpect(jsonPath("$[1].address", is("456 Elm St")))
				.andExpect(jsonPath("$[1].contactNumber", is("9876543210")))
				.andExpect(jsonPath("$[1].gender", is("Female")))
				.andExpect(jsonPath("$[1].aadharNo", is("987654321098")));
	}

	@Test
	public void testGetCustomerById() throws Exception {
		Customer customer = new Customer(1L, "Suresh Raina", "sureshraina@example.com", "123 Main St", "1234567890",
				"Male", "123456789012");

		when(customerService.getCustomerById(1L)).thenReturn(customer);

		mockMvc.perform(get("/customers/1")).andExpect(status().isOk()).andExpect(jsonPath("$.customerId", is(1)))
				.andExpect(jsonPath("$.customerName", is("Suresh Raina")))
				.andExpect(jsonPath("$.email", is("sureshraina@example.com")))
				.andExpect(jsonPath("$.address", is("123 Main St")))
				.andExpect(jsonPath("$.contactNumber", is("1234567890"))).andExpect(jsonPath("$.gender", is("Male")))
				.andExpect(jsonPath("$.aadharNo", is("123456789012")));
	}

	@Test
	public void testDeleteCustomer() throws Exception {
		// Mock the deleteCustomerById() method to return a string
		when(customerService.deleteCustomerById(1L)).thenReturn("Customer is Deleted");

		mockMvc.perform(delete("/customers/1")).andExpect(status().isOk())
				.andExpect(content().string(is("Customer is Deleted")));
	}

	@Test
    public void testUpdateCustomer() throws Exception {
        // Prepare customer data
        Customer customer = new Customer(1L, "John Doe", "john@example.com", "123 Main St", "1234567890", "Male", "123456789012");

        // Mock the service method
        when(customerService.updateCustomer(eq(1L), any(Customer.class))).thenReturn(customer);

        // Perform the PUT request
        RequestBuilder requestBuilder = put("/customers/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"customerId\": 1, \"customerName\": \"John Doe\", \"email\": \"john@example.com\", \"address\": \"123 Main St\", \"contactNumber\": \"1234567890\", \"gender\": \"Male\", \"aadharNo\": \"123456789012\"}");

        // Verify the response
        mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.customerId").value(1))
                .andExpect(jsonPath("$.customerName").value("John Doe"))
                .andExpect(jsonPath("$.email").value("john@example.com"))
                .andExpect(jsonPath("$.address").value("123 Main St"))
                .andExpect(jsonPath("$.contactNumber").value("1234567890"))
                .andExpect(jsonPath("$.gender").value("Male"))
                .andExpect(jsonPath("$.aadharNo").value("123456789012"));

        // Verify that the service method was called
        verify(customerService, times(1)).updateCustomer(eq(1L), any(Customer.class));
    }
}
