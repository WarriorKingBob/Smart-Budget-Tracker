package com.example.smartbudgettracker;

import com.example.smartbudgettracker.Account.Account;
import com.example.smartbudgettracker.Account.AccountService;
import com.example.smartbudgettracker.Category.Category;
import com.example.smartbudgettracker.Category.CategoryService;
import com.example.smartbudgettracker.Transaction.Transaction;
import com.example.smartbudgettracker.Transaction.TransactionService;
import com.example.smartbudgettracker.User.User;
import com.example.smartbudgettracker.User.UserService;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
public class TransactionServiceTest {
    @Autowired
    private TransactionService transactionService;
    @Autowired
    private AccountService accountService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private UserService userService;

    private Account account;
    private Category category;

    @BeforeEach
    void runBefore() {
        User user = userService.createUser("Bill", 5000);
        account = accountService.createAccount(user.getId(), "TD", Account.AccountType.CHEQUING, 10000);
        category = categoryService.createCategory("Groceries");
    }

    @Test
    void testCreateTransaction() {
        Transaction transaction = transactionService.createTransaction(account.getId(), category.getId(), Transaction.TransactionType.EXPENSE, 200, "Groceries at Costco", LocalDate.parse("2026-07-14"));
        assertNotNull(transaction.getId());
    }

    @Test
    void testGetAccountsByUserId() {
        Transaction transaction = transactionService.createTransaction(account.getId(), category.getId(), Transaction.TransactionType.EXPENSE, 200, "Groceries at Costco", LocalDate.parse("2026-07-14"));
        assertEquals(account.getId(), transaction.getAccount().getId());
    }

    @Test
    void testUpdateAccount() {
        Transaction transaction = transactionService.createTransaction(account.getId(), category.getId(), Transaction.TransactionType.EXPENSE, 200, "Groceries at Costco", LocalDate.parse("2026-07-14"));
        transactionService.updateAmount(transaction.getId(), 250);
        transactionService.updateDescription(transaction.getId(), "Groceries from Superstore");
        transactionService.updateTransactionDate(transaction.getId(), LocalDate.parse("2026-07-13"));
        assertEquals(250, transaction.getAmount());
        assertEquals("Groceries from Superstore", transaction.getDescription());
        assertEquals(LocalDate.parse("2026-07-13"), transaction.getTransactionDate());
    }

    @Test
    void testDeleteTransaction() {
        Transaction transaction = transactionService.createTransaction(account.getId(), category.getId(), Transaction.TransactionType.EXPENSE, 200, "Groceries at Costco", LocalDate.parse("2026-07-14"));
        transactionService.deleteTransaction(transaction.getId());
        List<Transaction> found = transactionService.getTransactionsByAccountId(account.getId());
        assertEquals(0, found.size());
    }
}
