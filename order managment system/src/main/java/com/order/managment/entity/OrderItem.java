package com.order.managment.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Entity
@Data
@Getter
@Setter
public class OrderItem {
    @Id
    @GeneratedValue
    private UUID id;

    private UUID orderId;
    private Integer quantity;
    private Integer cost;
    private String productId;
}
