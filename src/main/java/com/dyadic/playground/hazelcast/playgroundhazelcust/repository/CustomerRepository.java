package com.dyadic.playground.hazelcast.playgroundhazelcust.repository;

import com.dyadic.playground.hazelcast.playgroundhazelcust.domain.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends MongoRepository<Customer, String> {
}
