package com.learning.movieservice.dto;

import com.learning.movieservice.domain.Genre;

public record MovieDto(
        Integer id,
        String title,
        Integer releaseYear,
        Genre genre
) {
}
