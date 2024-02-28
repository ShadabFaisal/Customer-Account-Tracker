package com.cts.customer_account_tracker.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
public class Customer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long customerId;

	@NotEmpty
	@Size(min = 4, message = "Name should be atleast 4 characters long!!")
	private String customerName;

	@Email(regexp="^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$",message = "Email address is not Valid !!")
	private String email;

	@NotNull(message = "Provide Address ...")
	private String address;

	@NotEmpty(message = "Phone Number should not be Empty !")
	@Size(min = 10, message = "Contact Number should contain 10 digits !")
	private String contactNumber;

	@NotEmpty(message = "Must Specify your Gender...")
	private String gender;

	@NotEmpty(message = "aadharNo should not be Empty !!")
	@Size(min = 12, max = 12, message = "Your aadharNo should contain 12 digits !")
	private String aadharNo;

	public Customer() {
		super();
	}

	public Customer(Long customerId, String customerName, String email, String address, String contactNumber,
			String gender, String aadharNo) {
		super();
		this.customerId = customerId;
		this.customerName = customerName;
		this.email = email;
		this.address = address;
		this.contactNumber = contactNumber;
		this.gender = gender;
		this.aadharNo = aadharNo;
	}


	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getAadharNo() {
		return aadharNo;
	}

	public void setAadharNo(String aadharNo) {
		this.aadharNo = aadharNo;
	}

	@Override
	public String toString() {
		return "Customer [customerId=" + customerId + ", customerName=" + customerName + ", email=" + email
				+ ", address=" + address + ", contactNumber=" + contactNumber + ", gender=" + gender + ", AadharNo="
				+ aadharNo + ",]";
	}

}