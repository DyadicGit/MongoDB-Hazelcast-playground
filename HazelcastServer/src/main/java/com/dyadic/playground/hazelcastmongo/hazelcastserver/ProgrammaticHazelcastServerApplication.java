package com.dyadic.playground.hazelcastmongo.hazelcastserver;

import com.dyadic.playground.hazelcastmongo.hazelcastserver.config.ReadWriteThroughCache;
import com.hazelcast.config.Config;
import com.hazelcast.config.MapConfig;
import com.hazelcast.config.MapStoreConfig;
import com.hazelcast.core.HazelcastInstance;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import static com.hazelcast.core.Hazelcast.newHazelcastInstance;

@SpringBootApplication
public class ProgrammaticHazelcastServerApplication {
    public static void main(String[] args) {
        Config config = new Config();
        final MapConfig supplementsMapConfig = config.getMapConfig("company");

        final MapStoreConfig mapStoreConfig = supplementsMapConfig.getMapStoreConfig();
        mapStoreConfig
                .setEnabled(true)
                .setClassName("com.dyadic.playground.hazelcastmongo.hazelcastserver.config.CompanyMongoMapStore")
                .setProperty("mongo.url", "mongodb://localhost:27017")
                .setProperty("mongo.db", "test")
                .setProperty("mongo.collection", "company");

        final HazelcastInstance hazelcastInstance = newHazelcastInstance(config);
        new ReadWriteThroughCache(hazelcastInstance).run();
    }
}
