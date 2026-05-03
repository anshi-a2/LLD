package com.parkinglot;

import com.parkinglot.entities.ParkingFloor;
import com.parkinglot.entities.ParkingSpot;
import com.parkinglot.entities.ParkingTicket;
import com.parkinglot.strategy.feeStrategy.FeeStrategy;
import com.parkinglot.strategy.feeStrategy.FlatRateFeeStrategy;
import com.parkinglot.strategy.parkingStrategy.BestFitStrategy;
import com.parkinglot.strategy.parkingStrategy.ParkingStrategy;
import com.parkinglot.vehicle.Vehicle;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

public class ParkingLot {

    private static ParkingLot instance;
    private final List<ParkingFloor> floors = new CopyOnWriteArrayList<>();
    private final Map<String, ParkingTicket> activeTickets;
    private FeeStrategy feeStrategy;
    private ParkingStrategy parkingStrategy;

    public ParkingLot() {
        this.feeStrategy = new FlatRateFeeStrategy();
        this.parkingStrategy = new BestFitStrategy();
        this.activeTickets = new ConcurrentHashMap<>();

    }

    public static ParkingLot getInstance() {
        if(instance == null){
            instance = new ParkingLot();
        }
        return instance;
    }

    public void addFloor(ParkingFloor floor) {
        floors.add(floor);
    }

    public void setFeeStrategy (FeeStrategy feeStrategy) {
        this.feeStrategy = feeStrategy;
    }

    public void setParkingStrategy(ParkingStrategy parkingStrategy) {
        this.parkingStrategy = parkingStrategy;
    }

    public Optional<ParkingTicket> parkVehicle(Vehicle vehicle) {
        Optional<ParkingSpot> availableSpot = parkingStrategy.findSpot(floors, vehicle);

        if (availableSpot.isPresent()) {
            ParkingSpot spot = availableSpot.get();
            spot.parkVehicle(vehicle);
            ParkingTicket ticket = new ParkingTicket(vehicle, spot);
            activeTickets.put(vehicle.getLicenseNumber(), ticket);
            System.out.printf("%s parked at %s. Ticket: %s\n", vehicle.getLicenseNumber(), spot.getSpotId(), ticket.getTicketId());
            return Optional.of(ticket);
        }

        System.out.println("No available spot for " + vehicle.getLicenseNumber());
        return Optional.empty();
    }

    public Optional<Double> unparkVehicle(String licenseNumber) {
        ParkingTicket ticket = activeTickets.remove(licenseNumber);
        if (ticket == null) {
            System.out.println("Ticket not found");
            return Optional.empty();
        }

        ticket.setExitTimestamp();
        ticket.getSpot().unparkVehicle();

        Double parkingFee = feeStrategy.calculateFee(ticket);

        return Optional.of(parkingFee);
    }
}
