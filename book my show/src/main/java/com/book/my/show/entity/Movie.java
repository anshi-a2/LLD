package com.book.my.show.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.util.UUID;

@Entity
public class Movie {
    @Id
    @GeneratedValue
    private UUID id;

    private String title;
    private String language;
    private int durationInMinutes;
}

