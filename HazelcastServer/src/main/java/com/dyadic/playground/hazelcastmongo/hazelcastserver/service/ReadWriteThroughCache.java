package com.dyadic.playground.hazelcastmongo.hazelcastserver.service;

import com.dyadic.playground.hazelcastmongo.hazelcastserver.domain.Company;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IMap;

public class ReadWriteThroughCache {
    private final HazelcastInstance instance;

    public ReadWriteThroughCache(HazelcastInstance instance) {
        this.instance = instance;
    }

    public void run() {
        IMap<String, Company> companies = instance.getMap("company");
        System.out.println(companies.size());

        companies.loadAll(true);
        System.out.println(companies.size());
    }
}
