package com.learning.customerservice.dto;

import com.learning.customerservice.domain.Genre;

import java.util.List;

public record CustomerDto(Integer id, String name, Genre favoriteGenre, List<MovieDto> recommendedMovies) {
}
