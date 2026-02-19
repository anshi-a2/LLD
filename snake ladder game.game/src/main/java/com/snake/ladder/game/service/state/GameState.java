package com.snake.ladder.game.service.state;

import com.snake.ladder.game.entity.Game;
import com.snake.ladder.game.entity.GameStatus;
import com.snake.ladder.game.service.GameService;

public interface GameState {

    GameStatus getStatus();

    default void start(Game game, GameService service) {
        throw new IllegalStateException("Invalid state");
    }

    default String roll(Game game, GameService service) {
        throw new IllegalStateException("Invalid state");
    }
}

