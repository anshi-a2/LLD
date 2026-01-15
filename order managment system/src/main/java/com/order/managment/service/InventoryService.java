package com.order.managment.service;

import com.order.managment.entity.Inventory;
import com.order.managment.repository.InventoryRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InventoryService {

    @Autowired
    InventoryRepository inventoryRepository;

    @Transactional
    public void reserveStock(String productId, Integer qnty) {
        Inventory inventory = inventoryRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("product not found"));

        if(inventory.getAvailableQnty()-qnty<0){
            throw new RuntimeException("not enough stock");
        }
        inventory.setReservedQnty(inventory.getReservedQnty()+qnty);
        inventory.setAvailableQnty(inventory.getAvailableQnty()-qnty);
        inventoryRepository.save(inventory);

    }

    @Transactional
    public void releaseStock(String productId, Integer qnty) {
        Inventory inventory = inventoryRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("product not found"));
        inventory.setReservedQnty(inventory.getReservedQnty()-qnty);
        inventory.setAvailableQnty(inventory.getAvailableQnty()+qnty);
        inventoryRepository.save(inventory);
    }

    public String createProduct() {
        Inventory product = new Inventory();
        product.setAvailableQnty(20);
        product.setProductId("A");
        product.setProductName("product A");
        product.setReservedQnty(0);
        product.setCost(100);
        inventoryRepository.save(product);
        return product.getProductId();
    }
}
