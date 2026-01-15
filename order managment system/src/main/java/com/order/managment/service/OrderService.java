package com.order.managment.service;

import com.order.managment.entity.Inventory;
import com.order.managment.entity.Order;
import com.order.managment.entity.OrderItem;
import com.order.managment.entity.OrderStatus;
import com.order.managment.repository.OrderItemRepository;
import com.order.managment.repository.OrderRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class OrderService {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    OrderItemRepository orderItemRepository;

    @Autowired
    InventoryService inventoryService;

    @Transactional
    public UUID placeOrder(List<OrderItem> orderItems, String userId) {

        //1. create order
        //2. reserve inventory
        //3. payment order
        //4. create items

        //create order
        Integer totalCost = orderItems.stream().mapToInt(i -> i.getCost() * i.getQuantity()).sum();
        Order order = new Order();
        order.setOrderStatus(OrderStatus.ORDER_CREATED);
        order.setTotalCost(totalCost);
        order.setUserId(userId);
        Order savedOrder = orderRepository.save(order);

        //reserve inventory
        for(OrderItem orderItem : orderItems) {
            try{
                inventoryService.reserveStock(orderItem.getProductId(), orderItem.getQuantity());
            } catch (Exception e){
                order.setOrderStatus(OrderStatus.ORDER_CANCELLED);
                orderRepository.save(order);
                throw new RuntimeException("inventory shortage");
            }
        }
        order.setOrderStatus(OrderStatus.INVENTORY_RESERVED);
        orderRepository.save(order);

        //3. payment order

        boolean paymentStatus = Math.random() <0.8;
        if(!paymentStatus) {
            order.setOrderStatus(OrderStatus.ORDER_FAILED);
            orderRepository.save(order);
            rollbackInventory(orderItems);
            throw new RuntimeException("payment failed");
        }

        for (OrderItem item : orderItems) {
            item.setOrderId(savedOrder.getOrderId());
            orderItemRepository.save(item);
        }

        return savedOrder.getOrderId();
    }

    private void rollbackInventory(List<OrderItem> items) {
        for (OrderItem item : items) {
            inventoryService.releaseStock(item.getProductId(), item.getQuantity());
        }
    }
}
