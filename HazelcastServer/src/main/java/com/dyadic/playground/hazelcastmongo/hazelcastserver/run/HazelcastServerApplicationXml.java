package com.dyadic.playground.hazelcastmongo.hazelcastserver.run;

import com.dyadic.playground.hazelcastmongo.hazelcastserver.service.ReadWriteThroughCache;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class HazelcastServerApplicationXml {
    public static void main(String[] args) {
        final HazelcastInstance hazelcastInstance = Hazelcast.newHazelcastInstance();
        new ReadWriteThroughCache(hazelcastInstance).run();
    }
}
