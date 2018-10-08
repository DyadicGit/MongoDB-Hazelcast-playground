package com.dyadic.playground.mongocrud.service;

import com.dyadic.playground.mongocrud.domain.Company;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyServiceImpl implements CompanyService {
    private final MongoTemplate mongoTemplate;

    @Autowired
    public CompanyServiceImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public void addNewCompany(Company company) {
        mongoTemplate.save(company);
    }

    @Override
    public void addNewCompanies(List<Company> companies) {
        companies.forEach(mongoTemplate::save);
    }

    @Override
    public void dropCollectionCompany() {
        mongoTemplate.dropCollection(Company.class);
    }
}
