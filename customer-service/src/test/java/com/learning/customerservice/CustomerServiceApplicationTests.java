package com.learning.customerservice;

import com.learning.customerservice.client.MovieClient;
import com.learning.customerservice.domain.Genre;
import com.learning.customerservice.dto.CustomerDto;
import com.learning.customerservice.dto.GenreUpdateRequest;
import com.learning.customerservice.dto.MovieDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpMethod;
import org.springframework.http.ProblemDetail;
import org.springframework.http.RequestEntity;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.web.client.RestClient;

import java.net.URI;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;

@Import(TestcontainersConfiguration.class)
@MockitoBean(types = {RestClient.class, MovieClient.class})
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CustomerServiceApplicationTests {

    private static final Logger log = LoggerFactory.getLogger(CustomerServiceApplicationTests.class);

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private MovieClient movieClient;

    @Test
    public void health() {
        var responseEntity = this.restTemplate.getForEntity("/actuator/health", Object.class);
        Assertions.assertTrue(responseEntity.getStatusCode().is2xxSuccessful());
    }

    @Test
    public void customerWithMovies() {
        Mockito.when(movieClient.getMovies(any(Genre.class))).thenReturn(List.of(
                new MovieDto(1, "movie-1", 1990, Genre.ACTION),
                new MovieDto(2, "movie-2", 1991, Genre.ACTION)
        ));

        var responseEntity = this.restTemplate.getForEntity("/api/customers/1", CustomerDto.class);
        Assertions.assertTrue(responseEntity.getStatusCode().is2xxSuccessful());
        var customerDto = responseEntity.getBody();
        Assertions.assertNotNull(customerDto);
        Assertions.assertEquals("sam", customerDto.name());
        Assertions.assertEquals(2, customerDto.recommendedMovies().size());

    }

    @Test
    public void customerNotFound() {
        var responseEntity = this.restTemplate.getForEntity("/api/customers/10", ProblemDetail.class);
        Assertions.assertTrue(responseEntity.getStatusCode().is4xxClientError());
        var problemDetail = responseEntity.getBody();
        log.info("Problem detail: {}", problemDetail);
        Assertions.assertNotNull(problemDetail);
        Assertions.assertEquals("Customer not found", problemDetail.getTitle());

    }

    @Test
    public void updateGenre() {
        var genreUpdateRequest = new GenreUpdateRequest(Genre.DRAMA);
        var requestEntity = new RequestEntity<>(genreUpdateRequest, HttpMethod.PATCH, URI.create("/api/customers/1/genre"));
        var responseEntity = this.restTemplate.exchange(requestEntity, Void.class);
        Assertions.assertEquals(204, responseEntity.getStatusCode().value());
    }

}
