package com.cts.customer_account_tracker.Service.Impl;

import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.customer_account_tracker.Entity.User;
import com.cts.customer_account_tracker.Exceptions.UserNotFoundException;
import com.cts.customer_account_tracker.Exceptions.WeakPasswordException;
import com.cts.customer_account_tracker.Repository.UserRepository;
import com.cts.customer_account_tracker.Service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepo;

	public UserServiceImpl(UserRepository userRepository) {
		this.userRepo = userRepository;

	}

	@Override
	public User registerUser(User user) {
		// Save the User object using the userRepo
		User savedUser = null;
		String password=user.getPassword();
		if(!validatePassword(password)) {
			throw new WeakPasswordException();
		}
		else {			
			savedUser = userRepo.save(user);
		}
		System.out.println("User added successfully...");
		// Return the saved User object
		return savedUser;
	}

	@Override
	public User getUserById(String userId) {
		User user;
		if(userRepo.findById(userId).isEmpty()) {
			throw new UserNotFoundException("User Not Found");
		}
		else {
			user=userRepo.findById(userId).get();
		}
		return user;
	}

	
	@Override
	public String deleteUserById(String userId) {
		userRepo.deleteById(userId);
		return "User Info Deleted";
	}

	@Override
	public User updateUser(String userId, User user) {	
		User u=userRepo.findById(userId).orElseThrow(UserNotFoundException::new);
		u.setPassword(user.getPassword());
		User updatedUser = userRepo.save(u);
		System.out.println("Password Updated");
		return updatedUser;
	}

	@Override
	public User authenticateUser(String userId, String password) {
		// Retrieve the User by userName using the userRepo
		User user = userRepo.findById(userId).orElseThrow(UserNotFoundException::new);

		// Check if the provided password matches the User's password
		if (user==null || !user.getPassword().equals(password)) {
			throw new UserNotFoundException("Credentials do not Match!!! Please try again...");
		}

		return user;
	}
	
	public static boolean validatePassword(String password) {
        // Password length between 8 and 12 characters
        if (password.length() < 8) {
            return false;
        }

        // Complexity requirements (uppercase, lowercase, number, special character) Password should have:
//        At least one uppercase letter ([A-Z])
//        At least one lowercase letter ([a-z])
//        At least one numeric digit (\\d)
//        At least one special character from the set [@#$%^&+=]
        if (!Pattern.compile("[A-Z]").matcher(password).find() ||
                !Pattern.compile("[a-z]").matcher(password).find() ||
                !Pattern.compile("\\d").matcher(password).find() ||
                !Pattern.compile("[@#$%^&+=]").matcher(password).find()) {
            return false;
        }

        // Avoid common patterns or sequences
        String[] commonPatterns = {"123456", "password", "qwerty", "123456789", "12345678", "12345"};
        for (String pattern : commonPatterns) {
            if (password.equals(pattern)) {
                return false;
            }
        }

        // Avoid personal information
        String[] personalInfo = {"name", "username", "birthdate"}; // Add more personal information if needed
        for (String info : personalInfo) {
            if (password.toLowerCase().contains(info.toLowerCase())) {
                return false;
            }
        }

        return true;
    }



}
