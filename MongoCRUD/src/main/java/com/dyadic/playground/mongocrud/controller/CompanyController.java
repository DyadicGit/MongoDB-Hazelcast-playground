package com.dyadic.playground.mongocrud.controller;

import com.dyadic.playground.mongocrud.domain.Company;
import com.dyadic.playground.mongocrud.repository.CompanyRepository;
import com.dyadic.playground.mongocrud.service.CompanyService;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("company")
public class CompanyController {
    private final CompanyService companyService;
    private final CompanyRepository companyRepository;

    public CompanyController(CompanyService companyService, CompanyRepository companyRepository) {
        this.companyService = companyService;
        this.companyRepository = companyRepository;
    }


    @GetMapping("")
    String getAll(Model model) {
        List<Company> companies = companyRepository.findAll();
        model.addAttribute("companies", companies);
        return "api/companies";
    }

    @PostMapping(value = "", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    String saveCompany(Model model, Company company) {
        companyService.addNewCompany(company);
        model.addAttribute("companies", companyRepository.findAll());
        return "api/companies";
    }

    @PostMapping("/dropCollection")
    String dropCollection(Model model) {
        companyService.dropCollectionCompany();
        model.addAttribute("companies", companyRepository.findAll());
        return "api/companies";
    }
}
