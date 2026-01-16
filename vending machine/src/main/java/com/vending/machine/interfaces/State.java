package com.vending.machine.interfaces;

public interface State {
    void selectItem(String itemCode);
    void insertCoin(int amount);
    String dispense();
    String refund();
}

