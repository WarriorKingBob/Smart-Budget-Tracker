package com.example.smartbudgettracker.Transaction;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/users/{userId}/accounts/{accountId}/transactions")
public class TransactionController {
    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) { this.transactionService = transactionService; }

    public record CreateTransactionRequest(UUID categoryId, Transaction.TransactionType type, int amount, String description, LocalDate transactionDate) {}
    public record updateTypeRequest(Transaction.TransactionType newType) {}
    public record updateAmountRequest(int newAmount) {}
    public record updateDescriptionRequest(String newDescription) {}
    public record updateTransactionDateRequest(LocalDate newTransactionDate) {}

    @GetMapping("/{transactionId}")
    public ResponseEntity<Transaction> getTransactionById(@PathVariable UUID accountId, @PathVariable UUID transactionId) {
        return transactionService.getTransactionById(accountId, transactionId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<Transaction>> getTransactionsByUser(@PathVariable UUID accountId) {
        return ResponseEntity.ok(transactionService.getTransactionsByAccountId(accountId));
    }

    @PostMapping
    public ResponseEntity<Transaction> createTransaction(@PathVariable UUID accountId, @RequestBody CreateTransactionRequest body) {
        Transaction created = transactionService.createTransaction(accountId, body.categoryId(), body.type(), body.amount(), body.description(), body.transactionDate());
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/{transactionId}/type")
    public ResponseEntity<Transaction> updateType(@PathVariable UUID transactionId, @RequestBody updateTypeRequest body) {
        try {
            Transaction updated = transactionService.updateType(transactionId, body.newType());
            return ResponseEntity.ok(updated);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{transactionId}/amount")
    public ResponseEntity<Transaction> updateType(@PathVariable UUID transactionId, @RequestBody updateAmountRequest body) {
        try {
            Transaction updated = transactionService.updateAmount(transactionId, body.newAmount());
            return ResponseEntity.ok(updated);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{transactionId}/description")
    public ResponseEntity<Transaction> updateType(@PathVariable UUID transactionId, @RequestBody updateDescriptionRequest body) {
        try {
            Transaction updated = transactionService.updateDescription(transactionId, body.newDescription());
            return ResponseEntity.ok(updated);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{transactionId}/transactionDate")
    public ResponseEntity<Transaction> updateType(@PathVariable UUID transactionId, @RequestBody updateTransactionDateRequest body) {
        try {
            Transaction updated = transactionService.updateTransactionDate(transactionId, body.newTransactionDate());
            return ResponseEntity.ok(updated);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{transactionId}")
    public ResponseEntity<Transaction> deleteTransaction(@PathVariable UUID transactionId) {
        transactionService.deleteTransaction(transactionId);
        return ResponseEntity.noContent().build();
    }
}
