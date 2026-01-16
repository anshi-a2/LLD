package com.book.my.show.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data
@Getter
@Setter
public class Show {
    @Id
    @GeneratedValue
    private UUID id;

    @ManyToOne
    private Movie movie;

    @ManyToOne
    private Screen screen;

    private LocalDateTime startTime;
    private LocalDateTime endTime;
}

