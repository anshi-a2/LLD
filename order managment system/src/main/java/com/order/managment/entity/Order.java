package com.order.managment.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Entity(name = "orders")
@Getter
@Setter
public class Order {
    @Id
    @GeneratedValue
    private UUID orderId;
    private String userId;

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;
    private Integer totalCost;

}
