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

import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Answers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.cts.customer_account_tracker.Entity.User;
import com.cts.customer_account_tracker.Repository.UserRepository;
import com.cts.customer_account_tracker.Service.UserService;

class UserServiceImplTest {

	@Mock
	private UserRepository userRepository;
	private UserService userService;
	AutoCloseable autoCloseable;
	User user;
	User admin;

	@BeforeEach
	void setUp() throws Exception {
		autoCloseable = MockitoAnnotations.openMocks(this);
		userService = new UserServiceImpl(userRepository);
		user = new User("7865432156780918", "Piyush@123", false);
		admin = new User("admin", "Admin@123", true);
	}

	@AfterEach
	void tearDown() throws Exception {
		autoCloseable.close();
	}

	@Test
	void testRegisterUser() {
		mock(User.class);
		mock(UserRepository.class);

		when(userRepository.save(user)).thenReturn(user);
		when(userRepository.save(admin)).thenReturn(admin);

		assertThat(userService.registerUser(user)).isEqualTo(user);
		assertThat(userService.registerUser(admin)).isEqualTo(admin);
	}

	@Test
	void testDeleteUserById() {
		mock(User.class);
		mock(UserRepository.class, Mockito.CALLS_REAL_METHODS);

		doAnswer(Answers.CALLS_REAL_METHODS).when(userRepository).deleteById(any());

		assertThat(userService.deleteUserById("7865432156780918")).isEqualTo("User Info Deleted");
	}

	@Test
	public void testUpdateUser() {
		String userId = "123456789";
		User existingUser = new User();
		existingUser.setUserId(userId);
		existingUser.setPassword("oldPassword");

		User updatedUser = new User();
		updatedUser.setUserId(userId);
		updatedUser.setPassword("newPassword");

		when(userRepository.findById(userId)).thenReturn(Optional.of(existingUser));
		when(userRepository.save(any(User.class))).thenReturn(updatedUser);

		User result = userService.updateUser(userId, updatedUser);

		assertNotNull(result);
		assertEquals(userId, result.getUserId());
		assertEquals(updatedUser.getPassword(), result.getPassword());

		verify(userRepository, times(1)).findById(userId);
		verify(userRepository, times(1)).save(any(User.class));
	}

	@Test
	void testAuthenticateUser() {
		mock(User.class);
		mock(UserRepository.class);

		when(userRepository.findById("7865432156780918")).thenReturn(Optional.of(user));

		assertThat(userService.authenticateUser("7865432156780918", "Piyush@123")).isEqualTo(user);
	}

}
