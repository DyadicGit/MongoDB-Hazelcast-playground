package com.dyadic.playground.hazelcastmongo.hazelcastserver.mappers;

import java.io.Serializable;

public class Company implements Serializable {
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
}
