package com.dyadic.playground.hazelcastmongo.hazelcastserver.mappers;

import com.hazelcast.nio.serialization.Portable;
import com.hazelcast.nio.serialization.PortableReader;
import com.hazelcast.nio.serialization.PortableWriter;

import java.io.IOException;

public class Company implements Portable {
    final static int classId = 5;

    private String code;
    private String name;
    private String address;
    private String countyCode;

    public Company() {
    }

    public Company(String code, String name, String address, String countyCode) {
        this.code = code;
        this.name = name;
        this.address = address;
        this.countyCode = countyCode;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCountyCode() {
        return countyCode;
    }

    public void setCountyCode(String countyCode) {
        this.countyCode = countyCode;
    }

    @Override
    public int getFactoryId() {
        return 1;
    }

    @Override
    public int getClassId() {
        return classId;
    }

    @Override
    public void writePortable(PortableWriter writer) throws IOException {
        writer.writeUTF("code", code);
        writer.writeUTF("name", name);
        writer.writeUTF("address", address);
        writer.writeUTF("countyCode", countyCode);
    }

    @Override
    public void readPortable(PortableReader reader) throws IOException {
        code = reader.readUTF("code");
        name = reader.readUTF("name");
        address = reader.readUTF("address");
        countyCode = reader.readUTF("countyCode");
    }
}
