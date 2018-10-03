package com.dyadic.playground.mongocrud.controller;

import com.dyadic.playground.mongocrud.domain.Customer;
import com.dyadic.playground.mongocrud.repository.CustomerRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CustomerRestController {
    private CustomerRepository customerRepository;

    public CustomerRestController(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @RequestMapping("/customer")
    public List<Customer> getCustomers() {
        return customerRepository.findAll();
    }
}
