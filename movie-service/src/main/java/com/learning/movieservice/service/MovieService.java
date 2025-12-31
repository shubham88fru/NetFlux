package com.learning.movieservice.service;

import com.learning.movieservice.domain.Genre;
import com.learning.movieservice.dto.MovieDto;
import com.learning.movieservice.mapper.EntityDtoMapper;
import com.learning.movieservice.repository.MovieRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService {
    private final MovieRepository repository;
    public MovieService(MovieRepository repository) {
        this.repository = repository;
    }

    public List<MovieDto> getAll() {
        return this.repository.findAll().stream().map(EntityDtoMapper::toDto).toList();
    }

    public List<MovieDto> getAll(Genre genre) {
        return this.repository.findByGenre(genre).stream().map(EntityDtoMapper::toDto).toList();
    }
}
