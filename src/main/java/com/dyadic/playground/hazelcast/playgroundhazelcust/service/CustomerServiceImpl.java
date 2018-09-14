package com.dyadic.playground.hazelcast.playgroundhazelcust.service;

import com.dyadic.playground.hazelcast.playgroundhazelcust.domain.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public void addNewCustomer(Customer customer) {
        mongoTemplate.save(customer);
    }
}
