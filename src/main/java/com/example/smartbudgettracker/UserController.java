package com.example.smartbudgettracker;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }
    public record CreateUserRequest(String username, int monthlyIncome) {}
    public record UpdateUsernameRequest(String username) {}
    public record UpdateMonthlyIncomeRequest(int monthlyIncome) {}

    // GET all users
    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    // GET user by ID
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable UUID id) {
        return userService.getUserById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // POST create user
    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody CreateUserRequest body) {
        User created = userService.createUser(body.username(), body.monthlyIncome());
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/{id}/username")
    public ResponseEntity<User> updateUsername(@PathVariable UUID id, @RequestBody UpdateUsernameRequest body) {
        try {
            User updated = userService.updateUsername(id, body.username);
            return ResponseEntity.ok(updated);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}/monthly-income")
    public ResponseEntity<User> updateMonthlyIncome(@PathVariable UUID id, @RequestBody UpdateMonthlyIncomeRequest body) {
        try {
            User updated = userService.updateMonthlyIncome(id, body.monthlyIncome());
            return ResponseEntity.ok(updated);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // DELETE warehouse
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable UUID id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
