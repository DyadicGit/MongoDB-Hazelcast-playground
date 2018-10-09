package com.dyadic.playground.hazelcastmongo.hazelcastserver.run;

import com.dyadic.playground.hazelcastmongo.hazelcastserver.domain.CompanyPortableFactory;
import com.dyadic.playground.hazelcastmongo.hazelcastserver.service.ReadWriteThroughCache;
import com.dyadic.playground.hazelcastmongo.hazelcastserver.store.CompanyMongoMapStore;
import com.hazelcast.config.Config;
import com.hazelcast.config.MapConfig;
import com.hazelcast.config.MapStoreConfig;
import com.hazelcast.core.HazelcastInstance;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import static com.hazelcast.core.Hazelcast.newHazelcastInstance;

@SpringBootApplication
public class HazelcastServerApplicationProgrammatic {
    public static void main(String[] args) {
        Config config = new Config();
        final MapConfig supplementsMapConfig = config.getMapConfig("company");

        final MapStoreConfig mapStoreConfig = supplementsMapConfig.getMapStoreConfig();
        mapStoreConfig
                .setEnabled(true)
                .setClassName(CompanyMongoMapStore.class.getName())
                .setProperty("mongo.url", "mongodb://localhost:27017")
                .setProperty("mongo.db", "test")
                .setProperty("mongo.collection", "company");

        config.getSerializationConfig().addPortableFactory(1, new CompanyPortableFactory());
        final HazelcastInstance hazelcastInstance = newHazelcastInstance(config);
        new ReadWriteThroughCache(hazelcastInstance).run();
    }
}
