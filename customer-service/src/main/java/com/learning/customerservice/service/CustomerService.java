package com.learning.customerservice.service;

import com.learning.customerservice.client.MovieClient;
import com.learning.customerservice.dto.CustomerDto;
import com.learning.customerservice.dto.GenreUpdateRequest;
import com.learning.customerservice.exceptions.CustomerNotFoundException;
import com.learning.customerservice.mapper.EntityDtoMapper;
import com.learning.customerservice.repository.CustomerRepository;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    private final MovieClient movieClient;
    private final CustomerRepository customerRepository;

    public CustomerService(MovieClient movieClient, CustomerRepository customerRepository) {
        this.movieClient = movieClient;
        this.customerRepository = customerRepository;
    }

    public CustomerDto getCustomer(Integer id) {
        var customer = this.customerRepository.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException(id));

        var movies = this.movieClient.getMovies(customer.getFavoriteGenre());

        return EntityDtoMapper.toDto(customer, movies);
    }

    public void updateCustomerGenre(Integer id, GenreUpdateRequest genreUpdateRequest) {
        var customer = this.customerRepository.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException(id));

        customer.setFavoriteGenre(genreUpdateRequest.favoriteGenre());
        this.customerRepository.save(customer);
    }
}
