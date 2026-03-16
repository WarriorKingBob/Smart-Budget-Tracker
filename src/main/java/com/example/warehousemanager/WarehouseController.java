package com.example.warehousemanager;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/warehouses")
public class WarehouseController {

    private final UserService userService;

    public WarehouseController(UserService userService) {
        this.userService = userService;
    }

    // GET all warehouses
    @GetMapping
    public ResponseEntity<List<Warehouse>> getAllWarehouses() {
        return ResponseEntity.ok(userService.getAllWarehouses());
    }

    // GET warehouse by ID
    @GetMapping("/{id}")
    public ResponseEntity<Warehouse> getWarehouseById(@PathVariable Long id) {
        return userService.getWarehouseById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // POST create warehouse
    @PostMapping
    public ResponseEntity<Warehouse> createWarehouse(@RequestBody Map<String, String> body) {
        String name = body.get("name");
        String address = body.get("address");
        Warehouse created = userService.createWarehouse(name, address);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    // PUT update warehouse
    @PutMapping("/{id}")
    public ResponseEntity<Warehouse> updateWarehouse(@PathVariable Long id, @RequestBody Map<String, String> body) {
        try {
            String name = body.get("name");
            String address = body.get("address");
            Warehouse updated = userService.updateWarehouse(id, name, address);
            return ResponseEntity.ok(updated);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // DELETE warehouse
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteWarehouse(@PathVariable Long id) {
        userService.deleteWarehouse(id);
        return ResponseEntity.noContent().build();
    }
}
