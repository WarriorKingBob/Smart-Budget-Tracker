package com.example.smartbudgettracker.Transaction;

import com.example.smartbudgettracker.Account.Account;
import com.example.smartbudgettracker.Category.Category;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Transaction {
    public enum TransactionType {
        INCOME,
        EXPENSE
    }

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Getter
    private UUID id;
    @Setter @Getter private TransactionType type;
    @ManyToOne
    @JoinColumn(name = "account_id", nullable = false)
    @Getter @Setter private Account account;
    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    @Getter @Setter private Category category;
    @Min(1)
    @Getter @Setter private int amount;
    @Getter @Setter private String description;
    @Getter private LocalDateTime createdAt;
    @Getter @Setter private LocalDate transactionDate;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }

}
