package com.learning.customerservice.dto;


import com.learning.customerservice.domain.Genre;

public record MovieDto(
        Integer id,
        String title,
        Integer releaseYear,
        Genre genre
) {
}
