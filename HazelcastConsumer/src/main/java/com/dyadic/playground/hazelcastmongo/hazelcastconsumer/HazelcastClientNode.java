package com.dyadic.playground.hazelcastmongo.hazelcastconsumer;

import com.dyadic.playground.hazelcastmongo.hazelcastconsumer.domain.Company;
import com.dyadic.playground.hazelcastmongo.hazelcastconsumer.domain.CompanyPortableFactory;
import com.hazelcast.client.HazelcastClient;
import com.hazelcast.client.config.ClientConfig;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IMap;

import java.util.List;
import java.util.stream.Collectors;

class HazelcastClientNode {

    static void getCompanies() {
        ClientConfig config = new ClientConfig();
        config.getSerializationConfig().addPortableFactory(1, new CompanyPortableFactory());
        HazelcastInstance hazelcastInstance = HazelcastClient.newHazelcastClient(config);

        IMap<String, Company> customersList = hazelcastInstance.getMap("company");
        List<String> printResult = customersList.values()
                .stream()
                .map(s -> String.format("%s\t%s\t%s\t%s", s.getCode(), s.getName(), s.getAddress(), s.getCountyCode()))
                .collect(Collectors.toList());
        printResult.forEach(System.out::println);
    }
}
