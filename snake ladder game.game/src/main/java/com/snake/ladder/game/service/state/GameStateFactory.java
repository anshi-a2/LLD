package com.snake.ladder.game.service.state;

import com.snake.ladder.game.entity.GameStatus;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class GameStateFactory {

    public static GameState getState(GameStatus status) {
        return switch (status) {
            case CREATED -> new CreatedState();
            case IN_PROGRESS -> new InProgressState();
            case FINISHED -> new FinishedState();
            default -> throw new IllegalStateException("Invalid state");
        };
    }
}

