package com.dyadic.playground.hazelcastmongo.hazelcastconsumer;

import com.hazelcast.client.HazelcastClient;
import com.hazelcast.core.HazelcastInstance;

import java.util.List;

class HazelcastClientNode {
    static void getCustomersList() {
        HazelcastInstance hazelcastInstance = HazelcastClient.newHazelcastClient();
        List<String> customersList = hazelcastInstance.getList("customer");
        customersList.forEach(System.out::println);
    }

}
