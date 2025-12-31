package com.learning.movieservice.mapper;

import com.learning.movieservice.dto.MovieDto;
import com.learning.movieservice.entity.Movie;

public class EntityDtoMapper {

    public static MovieDto toDto(Movie movie) {
        return new MovieDto(
                movie.getId(),
                movie.getTitle(),
                movie.getReleaseYear(),
                movie.getGenre()
        );
    }
}
