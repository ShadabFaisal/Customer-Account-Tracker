package com.cts.customer_account_tracker.Controller;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cts.customer_account_tracker.Entity.User;
import com.cts.customer_account_tracker.Service.UserService;

import jakarta.validation.Valid;

@RestController
@CrossOrigin("*")
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserService userService;

	// Create a logger instance
	Logger logger = LoggerFactory.getLogger(UserController.class);

	@PostMapping("")
	public User saveUser(@RequestBody User user) {
		// Log an information message
		logger.info("Login credentials saved in Db");

		// Log a warning message
		logger.warn("Do not share your login credentials with others...");

		// Call the userService to save the user
		return userService.registerUser(user);
	}
	
	@GetMapping("/{userId}")
	public User getUserById(@PathVariable("userId")String userId) {
		return userService.getUserById(userId);
	}

	@DeleteMapping("/{userId}")
	public void deleteUserById(@PathVariable("userId") String userId) {
		userService.deleteUserById(userId);
	}

	@PutMapping("/{userId}")
	public User updateUser(@PathVariable("userId") String userId, @Valid @RequestBody User user) {
		return userService.updateUser(userId,user);
	}

	// Endpoint for user login authentication
	@PostMapping("/login")
	public User authenticateUser(@RequestParam String userId, @RequestParam String password) {
		// Call the login service to authenticate user credentials
		return userService.authenticateUser(userId, password);
	}
}
