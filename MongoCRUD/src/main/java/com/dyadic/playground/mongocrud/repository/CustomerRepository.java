package com.dyadic.playground.mongocrud.repository;

import com.dyadic.playground.mongocrud.domain.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends MongoRepository<Customer, String> {
}
