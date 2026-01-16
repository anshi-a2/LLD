package com.vending.machine.Entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Data
@Getter
@Setter
public class Item {

    @Id
    @GeneratedValue
    private Long id;

    private String code;
    private String name;
    private int price;

}

