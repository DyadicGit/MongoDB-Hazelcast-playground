package com.dyadic.playground.hazelcastmongo.hazelcastserver.domain;

import com.hazelcast.nio.serialization.Portable;
import com.hazelcast.nio.serialization.PortableFactory;

public class CompanyPortableFactory implements PortableFactory {
    @Override
    public Portable create(int classId) {
        if (Company.classId == classId) {
            return new Company();
        } else {
            return null;
        }
    }
}
