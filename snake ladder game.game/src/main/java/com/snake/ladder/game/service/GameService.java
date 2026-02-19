package com.snake.ladder.game.service;

import com.snake.ladder.game.entity.Game;
import com.snake.ladder.game.entity.GameStatus;
import com.snake.ladder.game.entity.Player;
import com.snake.ladder.game.repository.GameRepository;
import com.snake.ladder.game.repository.PlayerRepository;
import com.snake.ladder.game.service.state.GameState;
import com.snake.ladder.game.service.state.GameStateFactory;
import com.snake.ladder.game.service.strategy.MovementStrategy;
import jakarta.transaction.Transactional;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
@Getter
public class GameService {

    @Autowired
    private GameRepository gameRepo;
    @Autowired
    private PlayerRepository playerRepo;
    @Autowired private MovementStrategy movementStrategy;
    @Autowired private GameStateFactory stateFactory;

    @Autowired
    private DiceService diceService;

    public int rollDice() {
        return diceService.roll();
    }

    public Game createGame() {
        return gameRepo.save(new Game());
    }

    public Player addPlayer(Long gameId, String name) {
        Game game = gameRepo.findById(gameId).orElseThrow();

        int order = playerRepo.findByGameOrderByTurnOrder(game).size();

        Player p = new Player();
        p.setName(name);
        p.setTurnOrder(order);
        p.setGame(game);

        return playerRepo.save(p);
    }

    public void startGame(Long gameId) {
        Game game = gameRepo.findById(gameId).orElseThrow();
        game.initState(stateFactory);
        game.getState().start(game, this);
    }

    public String rollDice(Long gameId) {
        Game game = gameRepo.findById(gameId).orElseThrow();
        game.initState(stateFactory);
        return game.getState().roll(game, this);
    }

    public GameState getState(GameStatus status) {
        return stateFactory.getState(status);
    }
}

