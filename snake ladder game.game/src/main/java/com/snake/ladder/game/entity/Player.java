package com.snake.ladder.game.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Player {

    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private int position = 0;
    private int turnOrder;

    @ManyToOne
    private Game game;
}

