package com.vending.machine.Entities;

import com.vending.machine.Enums.MachineStateType;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Data
@Getter
@Setter
public class VendingMachine {

    @Id
    private Long id = 1L;

    @Enumerated(EnumType.STRING)
    private MachineStateType state;

    private int balance;

    @ManyToOne
    private Item selectedItem;

}
