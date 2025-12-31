package com.learning.movieservice.controller;

import com.learning.movieservice.domain.Genre;
import com.learning.movieservice.dto.MovieDto;
import com.learning.movieservice.service.MovieService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/movies")
public class MovieController {
    private final MovieService movieService;
    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping
    public List<MovieDto> getAll() {
        return this.movieService.getAll();
    }

    @GetMapping("{genre}")
    public List<MovieDto> getAllByGenre(@PathVariable Genre genre) {
        return this.movieService.getAll(genre);
    }
}
