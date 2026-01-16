package com.book.my.show.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

import java.util.UUID;

@Entity
public class Theatre {
    @Id
    @GeneratedValue
    private UUID id;

    private String name;

    @ManyToOne
    private City city;
}
