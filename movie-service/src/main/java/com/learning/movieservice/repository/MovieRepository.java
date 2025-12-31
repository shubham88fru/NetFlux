package com.learning.movieservice.repository;

import com.learning.movieservice.domain.Genre;
import com.learning.movieservice.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Integer> {
    List<Movie> findByGenre(Genre genre);
}
