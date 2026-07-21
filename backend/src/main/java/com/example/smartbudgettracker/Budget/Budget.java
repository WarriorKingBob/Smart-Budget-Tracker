package com.example.smartbudgettracker.Budget;

import com.example.smartbudgettracker.Category.Category;
import com.example.smartbudgettracker.User.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
public class Budget {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Getter
    private UUID id;
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @Getter @Setter
    private User user;
    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    @Getter @Setter
    private Category category;
    @Getter @Setter private BigDecimal limitAmount;
    @Min(1) @Max(12)
    @Getter @Setter private int month;
    @Min(2000)
    @Getter @Setter private int year;
}
