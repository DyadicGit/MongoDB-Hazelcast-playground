package com.dyadic.playground.hazelcastmongo.hazelcastserver;

import com.dyadic.playground.hazelcastmongo.hazelcastserver.service.MongoCrudAppService;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.util.List;

@Controller
class HazelcastServerNode {
    private MongoCrudAppService mongoCrudAppService;

    void populateWithCustomers() throws IOException {
        HazelcastInstance hazelcastInstance = Hazelcast.newHazelcastInstance();
        List<String> customerList = hazelcastInstance.getList("customer");
        List<String> responseFromMongoCRUD = mongoCrudAppService.getCustomersFromMongoCRUD();
        customerList.addAll(responseFromMongoCRUD);
    }

    @Autowired
    public void setMongoCrudAppService(MongoCrudAppService mongoCrudAppService) {
        this.mongoCrudAppService = mongoCrudAppService;
    }
}
