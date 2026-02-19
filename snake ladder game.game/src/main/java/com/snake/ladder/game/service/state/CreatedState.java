package com.snake.ladder.game.service.state;

import com.snake.ladder.game.entity.Game;
import com.snake.ladder.game.entity.GameStatus;
import com.snake.ladder.game.service.GameService;
import org.springframework.stereotype.Component;

@Component
public class CreatedState implements GameState {

    @Override
    public GameStatus getStatus() {
        return GameStatus.CREATED;
    }

    @Override
    public void start(Game game, GameService service) {
        game.setState(service.getState(GameStatus.IN_PROGRESS));
    }
}

