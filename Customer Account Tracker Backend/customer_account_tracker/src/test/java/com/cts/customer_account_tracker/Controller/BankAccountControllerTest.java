package com.cts.customer_account_tracker.Controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.cts.customer_account_tracker.Entity.BankAccount;
import com.cts.customer_account_tracker.Service.BankAccountService;

public class BankAccountControllerTest {

    @Mock
    private BankAccountService bankAccountService;

    @InjectMocks
    private BankAccountController bankAccountController;

    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(bankAccountController).build();
    }

    @Test
    public void testAddAccount() throws Exception {
        BankAccount mockAccount = new BankAccount("123456789", "Savings", 1000.0, "2023-06-08", null);
        when(bankAccountService.addAccount(any(BankAccount.class))).thenReturn(mockAccount);

        mockMvc.perform(post("/accounts")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"accNum\":\"123456789\",\"accType\":\"Savings\",\"accBal\":1000.0,\"accDate\":\"2023-06-08\",\"cust\":null}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.accNum").value("123456789"))
                .andExpect(jsonPath("$.accType").value("Savings"))
                .andExpect(jsonPath("$.accBal").value(1000.0))
                .andExpect(jsonPath("$.accDate").value("2023-06-08"));

        verify(bankAccountService, times(1)).addAccount(any(BankAccount.class));
    }

    @Test
    public void testGetAllAccounts() throws Exception {
        List<BankAccount> mockAccounts = new ArrayList<>();
        mockAccounts.add(new BankAccount("123456789", "Savings", 1000.0, "2023-06-08", null));
        when(bankAccountService.getAllAccounts()).thenReturn(mockAccounts);

        mockMvc.perform(get("/accounts"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].accNum").value("123456789"))
                .andExpect(jsonPath("$[0].accType").value("Savings"))
                .andExpect(jsonPath("$[0].accBal").value(1000.0))
                .andExpect(jsonPath("$[0].accDate").value("2023-06-08"));

        verify(bankAccountService, times(1)).getAllAccounts();
    }

    @Test
    public void testGetAccountById() throws Exception {
        BankAccount mockAccount = new BankAccount("123456789", "Savings", 1000.0, "2023-06-08", null);
        when(bankAccountService.getAccountById("123456789")).thenReturn(mockAccount);

        mockMvc.perform(get("/accounts/{accNum}", "123456789"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.accNum").value("123456789"))
                .andExpect(jsonPath("$.accType").value("Savings"))
                .andExpect(jsonPath("$.accBal").value(1000.0))
                .andExpect(jsonPath("$.accDate").value("2023-06-08"));

        verify(bankAccountService, times(1)).getAccountById("123456789");
    }

    @Test
    public void testDeleteAccount() throws Exception {
        mockMvc.perform(delete("/accounts/{accNum}", "123456789"))
                .andExpect(status().isOk());

        verify(bankAccountService, times(1)).deleteAccountById("123456789");
    }

}
