package com.snake.ladder.game.controller;

import com.snake.ladder.game.entity.Game;
import com.snake.ladder.game.entity.Player;
import com.snake.ladder.game.service.GameService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/games")
@RequiredArgsConstructor
public class GameController {

    private final GameService gameService;

    @PostMapping
    public Game createGame() {
        return gameService.createGame();
    }

    @PostMapping("/{id}/players")
    public Player addPlayer(@PathVariable Long id,
                            @RequestParam String name) {
        return gameService.addPlayer(id, name);
    }

    @PostMapping("/{id}/start")
    public void startGame(@PathVariable Long id) {
        gameService.startGame(id);
    }

    @PostMapping("/{id}/roll")
    public String roll(@PathVariable Long id) {
        return gameService.rollDice(id);
    }
}

