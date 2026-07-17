package com.example.smartbudgettracker.Account;

import com.example.smartbudgettracker.User.User;
import jakarta.persistence.*;
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
public class Account {
    public enum AccountType {
        CHEQUING,
        SAVINGS,
        CREDIT,
        INVESTMENT
    }
    public enum SupportedCurrency {
        CAD, USD, EUR, GBP, RMB
    }

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Getter private UUID id;
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @Getter @Setter private User user;
    @Setter @Getter private String bankName;
    @Getter private LocalDateTime createdAt;
    @Getter @Setter private LocalDate accountOpenedAt;
    @Setter @Getter private int balance;

    @Enumerated(EnumType.STRING)
    @Setter @Getter private AccountType accountType;

    @Enumerated(EnumType.STRING)
    @Setter @Getter private SupportedCurrency currency;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }
}
