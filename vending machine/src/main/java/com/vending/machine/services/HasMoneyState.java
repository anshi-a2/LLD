package com.vending.machine.services;

import com.vending.machine.interfaces.State;

public class HasMoneyState implements State {

    private final VendingMachineService service;

    public HasMoneyState(VendingMachineService service) {
        this.service = service;
    }

    @Override
    public void selectItem(String itemCode) {
        throw new RuntimeException("Item already selected");
    }

    @Override
    public void insertCoin(int amount) {
        service.addBalance(amount);
        if (service.isPaymentComplete()) {
            service.setState(MachineStateType.DISPENSING);
        }
    }

    @Override
    public String dispense() {
        throw new RuntimeException("Insufficient amount");
    }

    @Override
    public String refund() {
        int refund = service.resetBalance();
        service.reset();
        return "Refunded: " + refund;
    }
}

