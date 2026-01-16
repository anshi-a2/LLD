package com.book.my.show.entity;

import com.book.my.show.enums.SeatType;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Entity
@Data
@Setter
@Getter
public class Seat {
    @Id
    @GeneratedValue
    private UUID id;

    private String seatNumber;

    @Enumerated(EnumType.STRING)
    private SeatType seatType;

    @ManyToOne
    private Screen screen;
}

