package com.book.my.show.controller;

import com.book.my.show.entity.Movie;
import com.book.my.show.entity.Show;
import com.book.my.show.service.MovieService;
import com.book.my.show.service.ShowService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/movies")
public class MovieController {

    private final MovieService movieService;
    private final ShowService showService;

    public MovieController(MovieService movieService, ShowService showService) {
        this.movieService = movieService;
        this.showService = showService;
    }

    @GetMapping
    public List<Movie> getMovies() {
        return movieService.getAllMovies();
    }

    @GetMapping("/{movieId}/shows")
    public List<Show> getShows(@PathVariable UUID movieId) {
        return showService.getShowsByMovie(movieId);
    }
}

