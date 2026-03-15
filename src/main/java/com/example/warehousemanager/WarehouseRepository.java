package com.example.warehousemanager;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.*;

@Repository
public interface WarehouseRepository extends JpaRepository<Warehouse, Long>{
    // Generates: SELECT * FROM warehouse WHERE name = ?
    List<Warehouse> findByName(String name);

    // Generates: SELECT * FROM warehouse WHERE address LIKE ?
    List<Warehouse> findByAddressContaining(String keyword);

    // Generates: SELECT * FROM warehouse WHERE name = ? AND address = ?
    Optional<Warehouse> findByNameAndAddress(String name, String address);
}
