package com.cts.customer_account_tracker.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

//admin userId="admin" and password="Admin@123"
//user userId="{accNum}" and default password="Username@123"

@Entity
public class User {

	@Id
	private String userId;

	
	@NotNull
	@Size(min = 8, message = "Your password should contain atleast 8 characters.")
	private String password;

	private boolean role;

	public User() {

	}

	public User(String userId, String password, boolean role) {
		super();
		this.userId = userId;
		this.password = password;
		this.role = role;
	}

	
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	@JsonIgnore
	public String getPassword() {
		return password;
	}
	
	@JsonProperty
	public void setPassword(String password) {
		this.password = password;
	}

	public boolean getRole() {
		return role;
	}

	public void setRole(boolean role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", password=" + password + ", role=" + role + "]";
	}

}
