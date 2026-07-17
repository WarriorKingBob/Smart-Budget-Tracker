package com.example.smartbudgettracker;

import com.example.smartbudgettracker.Budget.Budget;
import com.example.smartbudgettracker.Budget.BudgetService;
import com.example.smartbudgettracker.Category.Category;
import com.example.smartbudgettracker.Category.CategoryService;
import com.example.smartbudgettracker.User.User;
import com.example.smartbudgettracker.User.UserService;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
public class BudgetServiceTest {
    @Autowired
    private BudgetService budgetService;
    @Autowired
    private UserService userService;
    @Autowired
    private CategoryService categoryService;

    @Test
    void testAddNewBudget() {
        User user = userService.createUser("Bill", 5000);
        Category category = categoryService.createCategory("Grocery");
        Budget budget = budgetService.createBudget(user.getId(), category.getId(), BigDecimal.valueOf(500), 1, 2026);
        assertNotNull(budget.getId());
    }

    @Test
    void testUpdateBudget() {
        User user = userService.createUser("Bill", 5000);
        Category category = categoryService.createCategory("Grocery");
        Budget budget = budgetService.createBudget(user.getId(), category.getId(), BigDecimal.valueOf(500), 1, 2026);
        budgetService.updateLimit(budget.getId(), BigDecimal.valueOf(400));
        budgetService.updateMonth(budget.getId(), 2);
        assertEquals(BigDecimal.valueOf(400), budget.getLimitAmount());
        assertEquals(2, budget.getMonth());
    }

    @Test
    void testDeleteBudget() {
        User user = userService.createUser("Bill", 5000);
        Category category = categoryService.createCategory("Grocery");
        Budget budget = budgetService.createBudget(user.getId(), category.getId(), BigDecimal.valueOf(500), 1, 2026);
        budgetService.deleteBudget(budget.getId());
        Optional<Budget> found = budgetService.getBudgetById(budget.getId());
        assertFalse(found.isPresent());
    }
}
