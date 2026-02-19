package com.snake.ladder.game.repository;

import com.snake.ladder.game.entity.Game;
import com.snake.ladder.game.entity.Ladder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LadderRepository extends JpaRepository<Ladder, Long> {

    List<Ladder> findByGame(Game game);
}

