package com.snake.ladder.game.entity;

import com.snake.ladder.game.service.state.GameState;
import com.snake.ladder.game.service.state.GameStateFactory;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Game {

    @Id
    @GeneratedValue
    private Long id;

    @Enumerated(EnumType.STRING)
    private GameStatus status = GameStatus.CREATED;

    private int boardSize = 100;

    private int currentTurnIndex = 0;

    @Transient
    private GameState state;

    public void initState(GameStateFactory factory) {
        this.state = factory.getState(this.status);
    }

    public void setState(GameState state) {
        this.state = state;
        this.status = state.getStatus();
    }
}

