package com.book.my.show.DTO;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Data
@Getter
@Setter
public class BookingRequest {
    private UUID userId;
    private UUID showId;
    private List<UUID> seatIds;
}

