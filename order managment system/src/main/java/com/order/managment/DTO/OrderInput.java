package com.order.managment.DTO;

import com.order.managment.entity.OrderItem;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Data
@Getter
@Setter
public class OrderInput {
    String userId;
    List<OrderItem> orderItems;
}
