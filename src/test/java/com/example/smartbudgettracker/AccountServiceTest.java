package com.example.smartbudgettracker;

import com.example.smartbudgettracker.Account.Account;
import com.example.smartbudgettracker.Account.AccountService;
import com.example.smartbudgettracker.User.User;
import com.example.smartbudgettracker.User.UserService;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class AccountServiceTest {
    @Autowired
    private AccountService accountService;
    @Autowired
    private UserService userService;

    // Create
    @Test
    void testCreateAccount() {
        User user = userService.createUser("Bill", 5000);
        Account testAccount = accountService.createAccount(user.getId(), "Test Bank", Account.AccountType.CHEQUING, 100);
        assertNotNull(testAccount.getId());
    }

    @Test
    void testGetAccountsByUserId() {
        User user = userService.createUser("Bill", 5000);
        Account account = accountService.createAccount(user.getId(), "Test Bank", Account.AccountType.CHEQUING, 100);
        assertEquals(user.getId(), account.getUser().getId());
    }

    @Test
    void testGetUserNoAccounts() {
        List<Account> found = accountService.getAccountsByUserId(UUID.randomUUID());
        assertEquals(0, found.size());
    }

    @Test
    void testUpdateAccount() {
        User user = userService.createUser("Bill", 5000);
        Account created = accountService.createAccount(user.getId(), "Test Bank", Account.AccountType.CHEQUING, 100);
        accountService.updateBankName(created.getId(), "New Bank");
        accountService.updateAccountType(created.getId(), Account.AccountType.CREDIT);
        accountService.updateBalance(created.getId(), 10000);
        assertEquals("New Bank", created.getBankName());
        assertEquals(Account.AccountType.CREDIT, created.getAccountType());
        assertEquals(10000, created.getBalance());
    }

    @Test
    void testDeleteAccount() {
        User user = userService.createUser("Bill", 5000);
        Account created = accountService.createAccount(user.getId(), "Test Bank", Account.AccountType.CHEQUING, 100);
        accountService.deleteAccount(created.getId());
        List<Account> found = accountService.getAccountsByUserId(user.getId());
        assertEquals(0, found.size());
    }
}
