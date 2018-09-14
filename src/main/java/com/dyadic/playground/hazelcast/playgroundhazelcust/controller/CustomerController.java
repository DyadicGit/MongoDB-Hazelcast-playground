package com.dyadic.playground.hazelcast.playgroundhazelcust.controller;

import com.dyadic.playground.hazelcast.playgroundhazelcust.domain.Customer;
import com.dyadic.playground.hazelcast.playgroundhazelcust.repository.CustomerRepository;
import com.dyadic.playground.hazelcast.playgroundhazelcust.service.CustomerService;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/customer")
public class CustomerController {
    private final CustomerRepository customerRepository;
    private final CustomerService customerService;

    public CustomerController(CustomerRepository customerRepository, CustomerService customerService) {
        this.customerRepository = customerRepository;
        this.customerService = customerService;
    }


    @GetMapping("")
    public String getAll(Model model) {
        model.addAttribute("msg", "Customers page");
        model.addAttribute("customers", customerRepository.findAll());
        return "api/customer";
    }

    @PostMapping(value = "", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String saveCustomer(Model model, Customer customer) {
        customerService.addNewCustomer(customer);
        model.addAttribute("msg", "Customers page");
        model.addAttribute("customers", customerRepository.findAll());
        return "api/customer";
    }
}
