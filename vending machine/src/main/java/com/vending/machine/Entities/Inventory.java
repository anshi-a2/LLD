package com.vending.machine.Entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Data
@Getter
@Setter
public class Inventory {

    @Id
    @GeneratedValue
    private Long id;

    @OneToOne
    private Item item;

    private int quantity;

}

