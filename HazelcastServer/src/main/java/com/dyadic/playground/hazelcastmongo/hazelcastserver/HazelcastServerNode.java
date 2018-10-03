package com.dyadic.playground.hazelcastmongo.hazelcastserver;

import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Collections;
import java.util.Map;

class HazelcastServerNode {

    static void populateWithCustomers() {
        HazelcastInstance hazelcastInstance = Hazelcast.newHazelcastInstance();
        Map<Long, String> customerMap = hazelcastInstance.getMap("customer");

        try {
            Map<Long, String> responseFromMongoCRUD = getCustomersFromMongoCRUD();
            customerMap.putAll(responseFromMongoCRUD);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static Map<Long, String> getCustomersFromMongoCRUD() throws IOException {
        URL url = new URL("http://localhost:8082/api/customer");
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");

        BufferedReader rd = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String line;
        while ((line = rd.readLine()) != null) {
            System.out.println(line);
        }
        rd.close();
        return Collections.emptyMap();
    }
}
