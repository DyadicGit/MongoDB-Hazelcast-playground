package com.dyadic.playground.hazelcastmongo.hazelcastconsumer;

import com.dyadic.playground.hazelcastmongo.hazelcastconsumer.mappers.Company;
import com.hazelcast.client.HazelcastClient;
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
        HazelcastInstance hazelcastInstance = HazelcastClient.newHazelcastClient();
        IMap<String, Company> customersList = hazelcastInstance.getMap("company");
        System.out.println("To Do: implement serialization of map above!");
        customersList.forEach((k, v) -> System.out.println(v));
    }
}
