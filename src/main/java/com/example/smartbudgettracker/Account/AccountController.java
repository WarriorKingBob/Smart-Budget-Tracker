package com.example.smartbudgettracker.Account;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/users/{userId}/accounts")
public class AccountController {
    private final AccountService accountService;

    public record CreateAccountRequest(String bankName, Account.AccountType accountType, int balance) {}
    public record updateBankNameRequest(String newBankName) {}
    public record updateAccountTypeRequest(Account.AccountType newAccountType) {}
    public record updateBalanceRequest(int newBalance) {}

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping
    public ResponseEntity<List<Account>> getAccountsByUser(@PathVariable UUID userId) {
        return ResponseEntity.ok(accountService.getAccountsByUserId(userId));
    }

    @GetMapping("/{accountId}")
    public ResponseEntity<Account> getAccountById(@PathVariable UUID userId, @PathVariable UUID accountId) {
        return accountService.getAccountById(userId, accountId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Account> createAccount(@PathVariable UUID userId, @RequestBody CreateAccountRequest body) {
        Account created = accountService.createAccount(userId, body.bankName(), body.accountType(), body.balance());
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/{accountId}/bank-name")
    public ResponseEntity<Account> updateBankName(@PathVariable UUID accountId, @RequestBody updateBankNameRequest body) {
        try {
            Account updated = accountService.updateBankName(accountId, body.newBankName());
            return ResponseEntity.ok(updated);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{accountId}/type")
    public ResponseEntity<Account> updateAccountType(@PathVariable UUID accountId, @RequestBody updateAccountTypeRequest body) {
        try {
            Account updated = accountService.updateAccountType(accountId, body.newAccountType());
            return ResponseEntity.ok(updated);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{accountId}/balance")
    public ResponseEntity<Account> updateBalance(@PathVariable UUID accountId, @RequestBody updateBalanceRequest body) {
        try {
            Account updated = accountService.updateBalance(accountId, body.newBalance());
            return ResponseEntity.ok(updated);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{accountId}")
    public ResponseEntity<Account> deleteAccount(@PathVariable UUID accountId) {
        accountService.deleteAccount(accountId);
        return ResponseEntity.noContent().build();
    }
}
