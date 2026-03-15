package com.example.warehousemanager;

import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class UserService {
    private final WarehouseRepository warehouseRepository;

    // Constructor injection
    public UserService(WarehouseRepository warehouseRepository) {
        this.warehouseRepository = warehouseRepository;
    }

    // Create
    public Warehouse createWarehouse(String name, String address) {
        Warehouse w = new Warehouse();
        w.setName(name);
        w.setAddress(address);

        return warehouseRepository.save(w); // Writes to DB
    }

    // Read
    public Optional<Warehouse> getWarehouseById(Long id) {
        return warehouseRepository.findById(id);
    }

    public List<Warehouse> getAllWarehouses() {
        return warehouseRepository.findAll();
    }

    // Update
    public Warehouse updateWarehouse(Long id, String newName, String newAddress) {
        Warehouse w = warehouseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Warehouse not found"));
        w.setName(newName);
        w.setAddress(newAddress);
        return warehouseRepository.save(w); // save() on existing entity = UPDATE
    }

    // Delete
    public void deleteWarehouse(Long id) {
        warehouseRepository.deleteById(id);
    }
}
