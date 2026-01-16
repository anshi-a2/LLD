package com.vending.machine.services;

import com.vending.machine.interfaces.State;

public class DispensingState implements State {

    private final VendingMachineService service;

    public DispensingState(VendingMachineService service) {
        this.service = service;
    }

    @Override
    public void selectItem(String itemCode) {
        throw new RuntimeException("Dispensing in progress");
    }

    @Override
    public void insertCoin(int amount) {
        throw new RuntimeException("Already paid");
    }

    @Override
    public String dispense() {
        String result = service.dispenseItemInternal();
        service.reset();
        return result;
    }

    @Override
    public String refund() {
        throw new RuntimeException("Cannot refund now");
    }
}

