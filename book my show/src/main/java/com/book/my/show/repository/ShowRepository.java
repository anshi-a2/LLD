package com.book.my.show.repository;

import com.book.my.show.entity.Show;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ShowRepository extends JpaRepository<Show, UUID> {

    List<Show> findByMovieId(UUID movieId);
}

