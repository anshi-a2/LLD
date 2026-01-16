package com.book.my.show.controller;

import com.book.my.show.DTO.BookingRequest;
import com.book.my.show.entity.Booking;
import com.book.my.show.service.BookingService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/bookings")
public class BookingController {

    private final BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @PostMapping
    public Booking book(@RequestBody BookingRequest request) {
        return bookingService.bookSeats(request);
    }
}
