package com.parkinglot.strategy.parkingStrategy;

import com.parkinglot.entities.ParkingFloor;
import com.parkinglot.entities.ParkingSpot;
import com.parkinglot.vehicle.Vehicle;

import java.util.List;
import java.util.Optional;

public interface ParkingStrategy {
    Optional<ParkingSpot> findSpot(List<ParkingFloor> floors, Vehicle vehicle);
}
