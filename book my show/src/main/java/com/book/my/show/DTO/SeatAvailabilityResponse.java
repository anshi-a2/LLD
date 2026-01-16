package com.book.my.show.DTO;

import com.book.my.show.enums.SeatStatus;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Data
@Getter
@Setter
public class SeatAvailabilityResponse {
    private UUID seatId;
    private String seatNumber;
    private SeatStatus status;
}

