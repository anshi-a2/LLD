package com.vending.machine.services;

import com.vending.machine.interfaces.State;

public class IdleState implements State {

    private final VendingMachineService service;

    public IdleState(VendingMachineService service) {
        this.service = service;
    }

    @Override
    public void selectItem(String itemCode) {
        service.selectItemInternal(itemCode);
        service.setState(MachineStateType.HAS_MONEY);
    }

    @Override
    public void insertCoin(int amount) {
        throw new RuntimeException("Select item first");
    }

    @Override
    public String dispense() {
        throw new RuntimeException("Insert money first");
    }

    @Override
    public String refund() {
        return "Nothing to refund";
    }
}

