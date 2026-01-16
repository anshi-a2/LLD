package com.book.my.show.service;

import com.book.my.show.DTO.BookingRequest;
import com.book.my.show.entity.Booking;
import com.book.my.show.entity.ShowSeat;
import com.book.my.show.enums.BookingStatus;
import com.book.my.show.enums.SeatStatus;
import com.book.my.show.repository.BookingRepository;
import com.book.my.show.repository.ShowSeatRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class BookingService {

    private final ShowSeatRepository showSeatRepository;
    private final BookingRepository bookingRepository;

    public BookingService(
            ShowSeatRepository showSeatRepository,
            BookingRepository bookingRepository
    ) {
        this.showSeatRepository = showSeatRepository;
        this.bookingRepository = bookingRepository;
    }

    @Transactional
    public Booking bookSeats(BookingRequest request) {

        List<ShowSeat> seats =
                showSeatRepository.lockAvailableSeats(
                        request.getShowId(),
                        request.getSeatIds()
                );

        if (seats.size() != request.getSeatIds().size()) {
            throw new RuntimeException("Seats not available");
        }

        seats.forEach(seat -> seat.setStatus(SeatStatus.BOOKED));
        showSeatRepository.saveAll(seats);

        Booking booking = new Booking();
        booking.setStatus(BookingStatus.CONFIRMED);
        booking.setCreatedAt(LocalDateTime.now());

        return bookingRepository.save(booking);
    }
}

