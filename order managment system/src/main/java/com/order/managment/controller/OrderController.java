package com.order.managment.controller;

import com.order.managment.DTO.OrderInput;
import com.order.managment.entity.Order;
import com.order.managment.entity.OrderItem;
import com.order.managment.service.InventoryService;
import com.order.managment.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    OrderService orderService;

    @Autowired
    InventoryService inventoryService;

    @PostMapping("/create")
    ResponseEntity<String> placeOrder(@RequestBody OrderInput orderInput) {
        try{
            UUID orderId = orderService.placeOrder(orderInput.getOrderItems(), orderInput.getUserId());
            return ResponseEntity.ok("created order with orderId" + orderId);
        } catch (Exception e){
            return ResponseEntity.internalServerError().body("exception occured");
        }

    }

    @PostMapping("/create/product")
    ResponseEntity<String> createPoduct() {
        return ResponseEntity.ok(inventoryService.createProduct());
    }
}
