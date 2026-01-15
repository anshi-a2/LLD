package com.order.managment.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Data
@Getter
@Setter
public class Inventory {
    @Id
    private String productId;
    private String productName;
    private Integer cost;
    private Integer availableQnty;
    private Integer reservedQnty;
}
