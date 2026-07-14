package com.example.smartbudgettracker.Budget;

import com.example.smartbudgettracker.Category.Category;
import com.example.smartbudgettracker.Category.CategoryRepository;
import com.example.smartbudgettracker.User.User;
import com.example.smartbudgettracker.User.UserRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class BudgetService {
    private final BudgetRepository budgetRepository;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;

    public BudgetService(BudgetRepository budgetRepository, UserRepository userRepository, CategoryRepository categoryRepository) {
        this.budgetRepository = budgetRepository;
        this.userRepository = userRepository;
        this.categoryRepository = categoryRepository;
    }

    public Budget createBudget(UUID userId, UUID categoryId, BigDecimal limitAmount, int month, int year) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new RuntimeException("Category not found"));
        Budget budget = new Budget();
        budget.setUser(user);
        budget.setCategory(category);
        budget.setLimitAmount(limitAmount);
        budget.setMonth(month);
        budget.setYear(year);

        return budgetRepository.save(budget);
    }

    // TODO: Add security so that a budget can only be accessed by use who made it
    public Optional<Budget> getBudgetById(UUID id) { return budgetRepository.findById(id); }

    public List<Budget> getBudgetsByUserId(UUID id) { return budgetRepository.findByUserId(id); }

    public Budget updateLimit(UUID id, BigDecimal newLimit) {
        Budget budget = budgetRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Budget not found"));
        budget.setLimitAmount(newLimit);
        return budgetRepository.save(budget);
    }

    public Budget updateMonth(UUID id, int newMonth) {
        Budget budget = budgetRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Budget not found"));
        budget.setMonth(newMonth);
        return budgetRepository.save(budget);
    }

    public Budget updateYear(UUID id, int newYear) {
        Budget budget = budgetRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Budget not found"));
        budget.setYear(newYear);
        return budgetRepository.save(budget);
    }

    public void deleteBudget(UUID id) { budgetRepository.deleteById(id); }
}
