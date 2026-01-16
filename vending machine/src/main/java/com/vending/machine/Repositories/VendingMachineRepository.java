package com.vending.machine.Repositories;

import com.vending.machine.Entities.VendingMachine;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VendingMachineRepository
        extends JpaRepository<VendingMachine, Long> {
}

