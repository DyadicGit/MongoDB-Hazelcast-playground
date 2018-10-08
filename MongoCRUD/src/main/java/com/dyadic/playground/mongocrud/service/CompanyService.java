package com.dyadic.playground.mongocrud.service;

import com.dyadic.playground.mongocrud.domain.Company;

import java.util.List;

public interface CompanyService {
    void addNewCompany(Company company);

    void addNewCompanies(List<Company> companies);

    void dropCollectionCompany();
}
