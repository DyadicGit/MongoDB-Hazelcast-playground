package com.dyadic.playground.hazelcastmongo.hazelcastserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.io.IOException;

@SpringBootApplication
public class HazelcastServerApplication {

    public static void main(String[] args) throws IOException {
        ApplicationContext applicationContext = SpringApplication.run(HazelcastServerApplication.class, args);
        HazelcastServerNode hazelcastServerNode = (HazelcastServerNode) applicationContext.getBean("hazelcastServerNode");
        hazelcastServerNode.populateWithCustomers();
    }
}
