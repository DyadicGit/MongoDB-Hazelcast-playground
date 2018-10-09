package com.dyadic.playground.hazelcastmongo.hazelcastconsumer;

import com.dyadic.playground.hazelcastmongo.hazelcastconsumer.mappers.Company;
import com.dyadic.playground.hazelcastmongo.hazelcastconsumer.mappers.CompanyPortableFactory;
import com.hazelcast.client.HazelcastClient;
import com.hazelcast.client.config.ClientConfig;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IMap;

import java.util.List;

class HazelcastClientNode {
    static void getCustomersList() {
        HazelcastInstance hazelcastInstance = HazelcastClient.newHazelcastClient();
        List<String> customersList = hazelcastInstance.getList("customer");
        customersList.forEach(System.out::println);
    }
    static void getCompanies() {
        ClientConfig config = new ClientConfig();
        config.getSerializationConfig().addPortableFactory(1, new CompanyPortableFactory());
        HazelcastInstance hazelcastInstance = HazelcastClient.newHazelcastClient(config);

        IMap<String, Company> customersList = hazelcastInstance.getMap("company");
        customersList.forEach((k, v) -> System.out.println(v));
    }
}
