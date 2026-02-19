package com.snake.ladder.game.repository;

import com.snake.ladder.game.entity.Game;
import com.snake.ladder.game.entity.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlayerRepository
        extends JpaRepository<Player, Long> {

    List<Player> findByGameOrderByTurnOrder(Game game);
}

