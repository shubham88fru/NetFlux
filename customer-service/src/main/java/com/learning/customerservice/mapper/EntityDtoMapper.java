package com.learning.customerservice.mapper;

import com.learning.customerservice.dto.CustomerDto;
import com.learning.customerservice.dto.MovieDto;
import com.learning.customerservice.entity.Customer;

import java.util.List;

public class EntityDtoMapper {
    public static CustomerDto toDto(Customer customer, List<MovieDto> movies) {
        return new CustomerDto(
                customer.getId(),
                customer.getName(),
                customer.getFavoriteGenre(),
                movies
        );
    }
}
