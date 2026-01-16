package com.book.my.show.service;

import com.book.my.show.DTO.SeatAvailabilityResponse;
import com.book.my.show.repository.ShowSeatRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class SeatService {

    private final ShowSeatRepository showSeatRepository;

    public SeatService(ShowSeatRepository showSeatRepository) {
        this.showSeatRepository = showSeatRepository;
    }

    public List<SeatAvailabilityResponse> getSeats(UUID showId) {
        return showSeatRepository.findByShowId(showId)
                .stream()
                .map(ss -> {
                    SeatAvailabilityResponse res = new SeatAvailabilityResponse();
                    res.setSeatId(ss.getSeat().getId());
                    res.setSeatNumber(ss.getSeat().getSeatNumber());
                    res.setStatus(ss.getStatus());
                    return res;
                })
                .toList();
    }
}

