package com.vending.machine.controller;

import com.vending.machine.services.VendingMachineService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/vending-machine")
public class VendingMachineController {

    private final VendingMachineService service;

    public VendingMachineController(VendingMachineService service) {
        this.service = service;
    }

    @PostMapping("/select/{itemCode}")
    public void selectItem(@PathVariable String itemCode) {
        service.selectItem(itemCode);
    }

    @PostMapping("/insert/{amount}")
    public void insertCoin(@PathVariable int amount) {
        service.insertCoin(amount);
    }

    @PostMapping("/dispense")
    public String dispense() {
        return service.dispense();
    }

    @PostMapping("/refund")
    public String refund() {
        return service.refund();
    }
}
