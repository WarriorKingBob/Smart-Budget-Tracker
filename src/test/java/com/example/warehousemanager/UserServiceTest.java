package com.example.warehousemanager;

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
    void testCreateWarehouse() {
        Warehouse testWarehouse = userService.createWarehouse("Bill Logistics", "Canada");
        assertNotNull(testWarehouse.getId()); // Check if DB assigned an ID
    }

    // Read single
    @Test
    void getWarehouseById() {
        Warehouse created = userService.createWarehouse("Bill Logistics", "Canada");
        Optional<Warehouse> found = userService.getWarehouseById(created.getId());
        assertTrue(found.isPresent());
        assertEquals(created.getId(), found.get().getId());
    }

    @Test
    void testGetWarehouseByIdNotFound() {
        Optional<Warehouse> found = userService.getWarehouseById(-1L);
        assertFalse(found.isPresent());
    }

    // Read all
    @Test
    void getAllWarehouses() {
        userService.createWarehouse("Warehouse A", "Canada");
        userService.createWarehouse("Warehouse B", "USA");
        List<Warehouse> warehouses = userService.getAllWarehouses();
        assertEquals(2, warehouses.size());
    }

    // Update
    @Test
    void updateWarehouse() {
        Warehouse created = userService.createWarehouse("Bill Logistics", "Canada");
        Warehouse updated = userService.updateWarehouse(created.getId(), "New Name", "New Address");
        assertEquals("New Name", updated.getName());
        assertEquals("New Address", updated.getAddress());
    }

    // Delete
    @Test
    void deleteWarehouse() {
        Warehouse created = userService.createWarehouse("Bill Logistics", "Canada");
        userService.deleteWarehouse(created.getId());
        Optional<Warehouse> found = userService.getWarehouseById(created.getId());
        assertFalse(found.isPresent());
    }
}