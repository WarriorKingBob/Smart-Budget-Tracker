package com.example.smartbudgettracker;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class UserServiceTest {

    @Autowired
    private UserService userService;

    // Create
    @Test
    void testCreateUser() {
        User testUser = userService.createUser("Bill", 3000);
        assertNotNull(testUser.getId()); // Check if DB assigned an ID
    }

    // Read single
    @Test
    void getUserById() {
        User created = userService.createUser("Bill", 3000);
        Optional<User> found = userService.getUserById(created.getId());
        assertTrue(found.isPresent());
        assertEquals(created.getId(), found.get().getId());
    }

    @Test
    void testGetUserByIdNotFound() {
        Optional<User> found = userService.getUserById(UUID.randomUUID());
        assertFalse(found.isPresent());
    }

    // Read all
    @Test
    void getAllUsers() {
        int before = userService.getAllUsers().size();
        userService.createUser("Poor Bill", 4000);
        userService.createUser("Rich Bill", 10000);
        List<User> users = userService.getAllUsers();
        assertEquals(before + 2, users.size());
    }

    // Update
    @Test
    void updateUser() {
        User user = userService.createUser("Bill", 3000);
        userService.updateUsername(user.getId(), "New Name");
        userService.updateMonthlyIncome(user.getId(), 5000);
        assertEquals("New Name", user.getUsername());
        assertEquals(5000, user.getMonthlyIncome());
    }

    // Delete
    @Test
    void deleteUser() {
        User created = userService.createUser("Bill", 4000);
        userService.deleteUser(created.getId());
        Optional<User> found = userService.getUserById(created.getId());
        assertFalse(found.isPresent());
    }
}