package com.snake.ladder.game.service.strategy;

import com.snake.ladder.game.entity.Game;
import com.snake.ladder.game.entity.Ladder;
import com.snake.ladder.game.entity.Snake;
import com.snake.ladder.game.repository.LadderRepository;
import com.snake.ladder.game.repository.SnakeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ClassicMovementStrategy implements MovementStrategy {

    @Autowired
    private SnakeRepository snakeRepo;
    @Autowired private LadderRepository ladderRepo;

    @Override
    public int move(int currentPos, int dice, Game game) {

        int next = currentPos + dice;

        if (next > game.getBoardSize()) return currentPos;

        for (Snake s : snakeRepo.findByGame(game)) {
            if (s.getStart() == next) return s.getEnd();
        }

        for (Ladder l : ladderRepo.findByGame(game)) {
            if (l.getStart() == next) return l.getEnd();
        }

        return next;
    }
}

