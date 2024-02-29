package com.cts.customer_account_tracker.Controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.cts.customer_account_tracker.Entity.User;
import com.cts.customer_account_tracker.Service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;

public class UserControllerTest {

    private MockMvc mockMvc;

    @Mock
    private UserService userService;
    
    @Mock
    private Logger logger;

    @InjectMocks
    private UserController userController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
    }
    
    
    @Test
    public void testSaveUser() throws Exception {
        User user = new User("123", "password123", true);
        given(userService.registerUser(any(User.class))).willReturn(user);

        mockMvc.perform(MockMvcRequestBuilders.post("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(user)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.userId").value(user.getUserId()))
                .andExpect(jsonPath("$.password").doesNotExist())
                .andExpect(jsonPath("$.role").value(user.getRole()));

        verify(userService, times(1)).registerUser(any(User.class));
        verify(logger, times(1)).info("Login credentials saved in Db");
        verify(logger, times(1)).warn("Do not share your login credentials with others...");
        verifyNoMoreInteractions(userService);
        verifyNoMoreInteractions(logger);
    }
    
    @Test
    public void testGetUserById() throws Exception {
        User user = new User("123", "password123", true);
        given(userService.getUserById(user.getUserId())).willReturn(user);

        mockMvc.perform(MockMvcRequestBuilders.get("/users/{userId}","123")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.userId").value(user.getUserId()))
                .andExpect(jsonPath("$.password").doesNotExist())
                .andExpect(jsonPath("$.role").value(user.getRole()));

        verify(userService, times(1)).getUserById(user.getUserId());
        verifyNoMoreInteractions(userService);
    }

    
    @Test
    public void testDeleteUserById() throws Exception {
        String userId = "12345";

        mockMvc.perform(MockMvcRequestBuilders.delete("/users/{userId}", userId)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(userService, times(1)).deleteUserById(userId);
        verifyNoMoreInteractions(userService);
    }
    
    @Test
    public void testUpdateUser() throws Exception {
        // Prepare user data
    	User user = new User("123", "password123", true);

        // Mock the service method
        when(userService.updateUser(eq("123"), any(User.class))).thenReturn(user);

        // Perform the PUT request
        RequestBuilder requestBuilder = put("/users/123")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"userId\":\"testuser\",\"password\":\"newpassword\",\"role\":true}");

        // Verify the response
        mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.userId").value("123"))
                .andExpect(jsonPath("$.password").doesNotExist())
                .andExpect(jsonPath("$.role").value(true));

        // Verify that the service method was called
        verify(userService, times(1)).updateUser(eq("123"), any(User.class));
    }
    
    
    @Test
    public void testAuthenticateUser() throws Exception {
        String userId = "testuser";
        String password = "testpassword";
        //User authenticatedUser = new User(userId, password, false);

        mockMvc.perform(MockMvcRequestBuilders.post("/users/login")
                .param("userId", userId)
                .param("password", password)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(userService, times(1)).authenticateUser(userId, password);
        verifyNoMoreInteractions(userService);
    }
    
    private static String asJsonString(Object obj) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
