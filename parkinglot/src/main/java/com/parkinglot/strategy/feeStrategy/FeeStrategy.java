package com.parkinglot.strategy.feeStrategy;

import com.parkinglot.entities.ParkingTicket;

public interface FeeStrategy {
    double calculateFee(ParkingTicket parkingTicket);
}
