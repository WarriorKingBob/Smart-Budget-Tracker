package com.example.smartbudgettracker.Budget;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/users/{userId}/budgets")
public class BudgetController {
    private final BudgetService budgetService;

    public BudgetController(BudgetService budgetService) { this.budgetService = budgetService; }

    public record createBudgetRequest(UUID categoryId, BigDecimal limitAmount, int month, int year) {}
    public record updateLimitAmountRequest(BigDecimal newLimitAmount) {}
    public record updateMonthRequest(int newMonth) {}
    public record updateYearRequest(int newYear) {}

    @GetMapping("/{budgetId}")
    public ResponseEntity<Budget> getBudgetById(@PathVariable UUID budgetId) {
        return budgetService.getBudgetById(budgetId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<Budget>> getBudgetsByUser(@PathVariable UUID userId) {
        return ResponseEntity.ok(budgetService.getBudgetsByUserId(userId));
    }

    @PostMapping
    public ResponseEntity<Budget> createBudget(@PathVariable UUID userId, @RequestBody createBudgetRequest body) {
        Budget created = budgetService.createBudget(userId, body.categoryId(), body.limitAmount(), body.month(), body.year());
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/{budgetId}/limit_amount")
    public ResponseEntity<Budget> updateLimitAmount(@PathVariable UUID budgetId, @RequestBody updateLimitAmountRequest body) {
        try {
            Budget updated = budgetService.updateLimit(budgetId, body.newLimitAmount());
            return ResponseEntity.ok(updated);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{budgetId}/month")
    public ResponseEntity<Budget> updateMonth(@PathVariable UUID budgetId, @RequestBody updateMonthRequest body) {
        try {
            Budget updated = budgetService.updateMonth(budgetId, body.newMonth());
            return ResponseEntity.ok(updated);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{budgetId}/year")
    public ResponseEntity<Budget> updateYear(@PathVariable UUID budgetId, @RequestBody updateYearRequest body) {
        try {
            Budget updated = budgetService.updateYear(budgetId, body.newYear());
            return ResponseEntity.ok(updated);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{budgetId}")
    public ResponseEntity<Budget> deleteBudget(@PathVariable UUID budgetId) {
        budgetService.deleteBudget(budgetId);
        return ResponseEntity.noContent().build();
    }
}
