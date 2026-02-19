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
public class Snake {
    @Id
    @GeneratedValue
    private Long id;
    private int start;
    private int end;
    @ManyToOne
    private Game game;
}

