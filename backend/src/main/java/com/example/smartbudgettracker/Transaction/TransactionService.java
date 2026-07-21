package com.example.smartbudgettracker.Transaction;

import com.example.smartbudgettracker.Account.Account;
import com.example.smartbudgettracker.Account.AccountRepository;
import com.example.smartbudgettracker.Category.Category;
import com.example.smartbudgettracker.Category.CategoryRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TransactionService {
    private final TransactionRepository transactionRepository;
    private final AccountRepository accountRepository;
    private final CategoryRepository categoryRepository;

    public TransactionService(TransactionRepository transactionRepository, AccountRepository accountRepository, CategoryRepository categoryRepository) {
        this.transactionRepository = transactionRepository;
        this.accountRepository = accountRepository;
        this.categoryRepository = categoryRepository;
    }

    public Transaction createTransaction(UUID accountId, UUID categoryId, Transaction.TransactionType type, int amount, String description, LocalDate transactionDate) {
        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new RuntimeException("Account not found"));
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new RuntimeException("Category not found"));
        Transaction transaction = new Transaction();
        transaction.setAccount(account);
        transaction.setCategory(category);
        transaction.setType(type);
        transaction.setAmount(amount);
        transaction.setDescription(description);
        transaction.setTransactionDate(transactionDate);

        return transactionRepository.save(transaction);
    }

    public Optional<Transaction> getTransactionById(UUID accountId, UUID transactionId) {
        Transaction transaction = transactionRepository.findById(transactionId)
                .orElseThrow(() -> new RuntimeException("Transaction not found"));
        if (transactionRepository.findByAccountId(accountId).contains(transaction)) {
            return transactionRepository.findById(transactionId);
        } else {
            throw new RuntimeException("Transaction not found");
        }
    }

    public List<Transaction> getTransactionsByAccountId(UUID id) { return transactionRepository.findByAccountId(id); }

    public Transaction updateType(UUID transactionId, Transaction.TransactionType newType) {
        Transaction transaction = transactionRepository.findById(transactionId)
                .orElseThrow(() -> new RuntimeException("Transaction not found"));
        transaction.setType(newType);
        return transactionRepository.save(transaction);
    }

    public Transaction updateAmount(UUID transactionId, int newAmount) {
        Transaction transaction = transactionRepository.findById(transactionId)
                .orElseThrow(() -> new RuntimeException("Transaction not found"));
        transaction.setAmount(newAmount);
        return transactionRepository.save(transaction);
    }

    public Transaction updateDescription(UUID transactionId, String newDescription) {
        Transaction transaction = transactionRepository.findById(transactionId)
                .orElseThrow(() -> new RuntimeException("Transaction not found"));
        transaction.setDescription(newDescription);
        return transactionRepository.save(transaction);
    }

    public Transaction updateTransactionDate(UUID transactionId, LocalDate newTransactionDate) {
        Transaction transaction = transactionRepository.findById(transactionId)
                .orElseThrow(() -> new RuntimeException("Transaction not found"));
        transaction.setTransactionDate(newTransactionDate);
        return transactionRepository.save(transaction);
    }

    public void deleteTransaction(UUID id) { transactionRepository.deleteById(id); }
}
