package com.learning.customerservice.dto;

import com.learning.customerservice.domain.Genre;

public record GenreUpdateRequest(Genre favoriteGenre) {
}
