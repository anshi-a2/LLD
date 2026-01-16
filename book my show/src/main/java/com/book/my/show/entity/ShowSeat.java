package com.book.my.show.entity;

import com.book.my.show.enums.SeatStatus;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(
        uniqueConstraints = @UniqueConstraint(
                columnNames = {"show_id", "seat_id"}
        )
)
@Data
@Getter
@Setter
public class ShowSeat {

    @Id
    @GeneratedValue
    private UUID id;

    @ManyToOne
    private Show show;

    @ManyToOne
    private Seat seat;

    @Enumerated(EnumType.STRING)
    private SeatStatus status;
}
