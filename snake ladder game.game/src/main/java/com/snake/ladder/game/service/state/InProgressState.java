package com.snake.ladder.game.service.state;

import com.snake.ladder.game.entity.Game;
import com.snake.ladder.game.entity.GameStatus;
import com.snake.ladder.game.entity.Player;
import com.snake.ladder.game.service.GameService;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class InProgressState implements GameState {

    @Override
    public GameStatus getStatus() {
        return GameStatus.IN_PROGRESS;
    }

    @Override
    public String roll(Game game, GameService service) {

        List<Player> players =
                service.getPlayerRepo().findByGameOrderByTurnOrder(game);

        Player current = players.get(game.getCurrentTurnIndex());

        int dice = service.getDiceService().roll();

        int newPos = service.getMovementStrategy()
                .move(current.getPosition(), dice, game);

        current.setPosition(newPos);

        if (newPos == game.getBoardSize()) {
            game.setState(service.getState(GameStatus.FINISHED));
            return current.getName() + " won!";
        }

        game.setCurrentTurnIndex(
                (game.getCurrentTurnIndex() + 1) % players.size()
        );

        return current.getName() + " rolled " + dice +
                " moved to " + newPos;
    }
}

