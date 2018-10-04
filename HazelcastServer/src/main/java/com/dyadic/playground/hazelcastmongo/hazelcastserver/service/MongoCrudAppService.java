package com.dyadic.playground.hazelcastmongo.hazelcastserver.service;

import java.io.IOException;
import java.util.List;

public interface MongoCrudAppService {
    List<String> getCustomersFromMongoCRUD() throws IOException;
}
