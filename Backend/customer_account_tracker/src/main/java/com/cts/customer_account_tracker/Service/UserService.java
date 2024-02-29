package com.cts.customer_account_tracker.Service;

import com.cts.customer_account_tracker.Entity.User;

public interface UserService {
	
	public User registerUser(User user);
	public User getUserById(String userId);
	public String deleteUserById(String userId);
	public User updateUser(String userId,User user);
	public User authenticateUser(String userId, String password);
}
