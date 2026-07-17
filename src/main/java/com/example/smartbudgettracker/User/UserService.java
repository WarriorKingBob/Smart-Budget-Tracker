package com.example.smartbudgettracker.User;

import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class UserService {
    private final UserRepository userRepository;

    // Constructor injection
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Create
    public User createUser(String name, int monthlyIncome) {
        User u = new User();
        u.setUsername(name);
        u.setMonthlyIncome(monthlyIncome);

        return userRepository.save(u); // Writes to DB
    }

    // Read
    public Optional<User> getUserById(UUID id) {
        return userRepository.findById(id);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // Update username
    public User updateUsername(UUID id, String newUsername) {
        User u = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        u.setUsername(newUsername);
        return userRepository.save(u);
    }

    // Update income
    public User updateMonthlyIncome(UUID id, int newMonthlyIncome) {
        User u = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        u.setMonthlyIncome(newMonthlyIncome);
        return userRepository.save(u);
    }

    // Delete
    public void deleteUser(UUID id) {
        userRepository.deleteById(id);
    }
}
