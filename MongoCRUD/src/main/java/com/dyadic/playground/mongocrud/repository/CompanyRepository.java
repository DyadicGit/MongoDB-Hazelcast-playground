package com.dyadic.playground.mongocrud.repository;

import com.dyadic.playground.mongocrud.domain.Company;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRepository extends MongoRepository<Company, String> {
}
