package com.snake.ladder.game.repository;

import com.snake.ladder.game.entity.Game;
import com.snake.ladder.game.entity.Snake;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SnakeRepository extends JpaRepository<Snake, Long> {

    List<Snake> findByGame(Game game);
}

