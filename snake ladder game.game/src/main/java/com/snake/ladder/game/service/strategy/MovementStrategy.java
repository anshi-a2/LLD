package com.snake.ladder.game.service.strategy;

import com.snake.ladder.game.entity.Game;

public interface MovementStrategy {
    int move(int currentPos, int dice, Game game);
}

