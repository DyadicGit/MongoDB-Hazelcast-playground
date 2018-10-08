package com.dyadic.playground.mongocrud.service;

import com.dyadic.playground.mongocrud.domain.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final MongoTemplate mongoTemplate;

    @Autowired
    public CustomerServiceImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public void addNewCustomer(Customer customer) {
        mongoTemplate.save(customer);
    }
}
