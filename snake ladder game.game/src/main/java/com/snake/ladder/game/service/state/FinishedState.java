package com.snake.ladder.game.service.state;

import com.snake.ladder.game.entity.Game;
import com.snake.ladder.game.entity.GameStatus;
import com.snake.ladder.game.service.GameService;
import org.springframework.stereotype.Component;

@Component
public class FinishedState implements GameState {

    @Override
    public GameStatus getStatus() {
        return GameStatus.FINISHED;
    }

    @Override
    public String roll(Game game, GameService service) {
        return "Game already finished.";
    }
}

