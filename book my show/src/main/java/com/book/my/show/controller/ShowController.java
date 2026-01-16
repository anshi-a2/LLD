package com.book.my.show.controller;

import com.book.my.show.DTO.SeatAvailabilityResponse;
import com.book.my.show.service.SeatService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/shows")
public class ShowController {

    private final SeatService seatService;

    public ShowController(SeatService seatService) {
        this.seatService = seatService;
    }

    @GetMapping("/{showId}/seats")
    public List<SeatAvailabilityResponse> getSeats(
            @PathVariable UUID showId
    ) {
        return seatService.getSeats(showId);
    }
}
