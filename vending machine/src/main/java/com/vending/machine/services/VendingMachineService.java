package com.vending.machine.services;

import com.vending.machine.Entities.Inventory;
import com.vending.machine.Entities.Item;
import com.vending.machine.Entities.VendingMachine;
import com.vending.machine.Enums.MachineStateType;
import com.vending.machine.Repositories.InventoryRepository;
import com.vending.machine.Repositories.ItemRepository;
import com.vending.machine.Repositories.VendingMachineRepository;
import com.vending.machine.interfaces.State;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@Transactional
public class VendingMachineService {

    private final ItemRepository itemRepo;
    private final InventoryRepository inventoryRepo;
    private final VendingMachineRepository machineRepo;

    private final Map<MachineStateType, State> states = new HashMap<>();

    public VendingMachineService(
            ItemRepository itemRepo,
            InventoryRepository inventoryRepo,
            VendingMachineRepository machineRepo
    ) {
        this.itemRepo = itemRepo;
        this.inventoryRepo = inventoryRepo;
        this.machineRepo = machineRepo;

        states.put(MachineStateType.IDLE, new IdleState(this));
        states.put(MachineStateType.HAS_MONEY, new HasMoneyState(this));
        states.put(MachineStateType.DISPENSING, new DispensingState(this));
    }

    private VendingMachine getMachine() {
        return machineRepo.findById(1L)
                .orElseGet(() -> {
                    VendingMachine vm = new VendingMachine();
                    vm.setState(MachineStateType.IDLE);
                    vm.setBalance(0);
                    return machineRepo.save(vm);
                });
    }

    public void setState(MachineStateType state) {
        VendingMachine vm = getMachine();
        vm.setState(state);
        machineRepo.save(vm);
    }

    public State currentState() {
        return states.get(getMachine().getState());
    }

    public void selectItem(String itemCode) {
        currentState().selectItem(itemCode);
    }

    public void insertCoin(int amount) {
        currentState().insertCoin(amount);
    }

    public String dispense() {
        return currentState().dispense();
    }

    public String refund() {
        return currentState().refund();
    }

    // ---------- Internal Helpers ----------

    void selectItemInternal(String itemCode) {
        Item item = itemRepo.findByCode(itemCode)
                .orElseThrow(() -> new RuntimeException("Item not found"));

        Inventory inventory = inventoryRepo.findByItem(item)
                .orElseThrow(() -> new RuntimeException("Out of stock"));

        if (inventory.getQuantity() <= 0) {
            throw new RuntimeException("Item out of stock");
        }

        VendingMachine vm = getMachine();
        vm.setSelectedItem(item);
        machineRepo.save(vm);
    }

    void addBalance(int amount) {
        VendingMachine vm = getMachine();
        vm.setBalance(vm.getBalance() + amount);
        machineRepo.save(vm);
    }

    boolean isPaymentComplete() {
        VendingMachine vm = getMachine();
        return vm.getBalance() >= vm.getSelectedItem().getPrice();
    }

    String dispenseItemInternal() {
        VendingMachine vm = getMachine();
        Item item = vm.getSelectedItem();

        Inventory inventory = inventoryRepo.findByItem(item).get();
        inventory.setQuantity(inventory.getQuantity() - 1);

        int change = vm.getBalance() - item.getPrice();

        inventoryRepo.save(inventory);

        return "Dispensed " + item.getName() +
                (change > 0 ? ", Change: " + change : "");
    }

    int resetBalance() {
        VendingMachine vm = getMachine();
        int balance = vm.getBalance();
        vm.setBalance(0);
        machineRepo.save(vm);
        return balance;
    }

    void reset() {
        VendingMachine vm = getMachine();
        vm.setBalance(0);
        vm.setSelectedItem(null);
        vm.setState(MachineStateType.IDLE);
        machineRepo.save(vm);
    }
}
