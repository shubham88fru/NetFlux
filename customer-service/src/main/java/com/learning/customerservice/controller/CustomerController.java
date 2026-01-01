package com.learning.customerservice.controller;

import com.learning.customerservice.dto.CustomerDto;
import com.learning.customerservice.dto.GenreUpdateRequest;
import com.learning.customerservice.service.CustomerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    private final CustomerService customerService;
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerDto> getCustomer(@PathVariable Integer id) {
        var customerDto = customerService.getCustomer(id);
        return ResponseEntity.ok(customerDto);
    }

    @PatchMapping("/{id}/genre")
    public ResponseEntity<Void> updateGenre(@PathVariable Integer id, @RequestBody GenreUpdateRequest request) {
        this.customerService.updateCustomerGenre(id, request);
        return ResponseEntity.noContent().build(); //status 204
    }

}
