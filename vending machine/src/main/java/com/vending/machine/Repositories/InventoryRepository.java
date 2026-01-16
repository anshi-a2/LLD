package com.vending.machine.Repositories;

import com.vending.machine.Entities.Inventory;
import com.vending.machine.Entities.Item;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface InventoryRepository extends JpaRepository<Inventory, Long> {
    Optional<Inventory> findByItem(Item item);
}

