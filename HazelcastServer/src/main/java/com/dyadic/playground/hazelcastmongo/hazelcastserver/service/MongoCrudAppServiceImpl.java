package com.dyadic.playground.hazelcastmongo.hazelcastserver.service;

import com.dyadic.playground.hazelcastmongo.hazelcastserver.mappers.Customer;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MongoCrudAppServiceImpl implements MongoCrudAppService, InitializingBean {
    @Value("${app-mongo-crud.url.base}")
    private String baseUrl;
    @Value("${app-mongo-crud.url.customer}")
    private String customerUrl;
    private String apiCustomerUrl;

    @Override
    public List<String> getCustomersFromMongoCRUD() throws IOException {
        URL url = new URL(apiCustomerUrl);
        ObjectMapper objectMapper = new ObjectMapper();
        List<Customer> customers = objectMapper.readValue(url, new TypeReference<List<Customer>>() {
        });
        return customers.stream()
                .map(customer ->
                        Optional.ofNullable(customer.getName()).orElse("--") +", "+ Optional.ofNullable(customer.getSurname()).orElse("--")
                ).collect(Collectors.toList());
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        this.setApiCustomerUrl(String.format("%s%s", this.baseUrl, this.customerUrl));
    }

    private void setApiCustomerUrl(String apiCustomerUrl) {
        this.apiCustomerUrl = apiCustomerUrl;
    }
}
