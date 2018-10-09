package com.dyadic.playground.hazelcastmongo.hazelcastserver.store;

import com.dyadic.playground.hazelcastmongo.hazelcastserver.domain.Company;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.MapLoaderLifecycleSupport;
import com.hazelcast.core.MapStore;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.BulkWriteOptions;
import com.mongodb.client.model.InsertOneModel;
import com.mongodb.client.model.Projections;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.util.*;
import java.util.stream.Collectors;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Filters.in;

public class CompanyMongoMapStore implements MapStore<String, Company>, MapLoaderLifecycleSupport {
    private MongoClient mongoClient;
    private MongoCollection mongoCollection;

    @Override
    public void init(HazelcastInstance hazelcastInstance, Properties properties, String mapName) {
        String mongoUrl = (String) properties.get("mongo.url");
        String dbName = (String) properties.get("mongo.db");
        String collectionName = (String) properties.get("mongo.collection");
        this.mongoClient = new MongoClient(new MongoClientURI(mongoUrl));
        this.mongoCollection = mongoClient.getDatabase(dbName).getCollection(collectionName);
    }

    @Override
    public void destroy() {
        mongoClient.close();
    }

    @Override
    public void store(String key, Company value) {
        Document doc = new Document("code", value.getCode())
                .append("name", value.getName())
                .append("address", value.getAddress())
                .append("countyCode", value.getCountyCode())
                .append("_id", key);
        this.mongoCollection.insertOne(doc);
    }

    @Override
    public void storeAll(Map<String, Company> map) {
        List<InsertOneModel> batch = new LinkedList<InsertOneModel>();
        for (Map.Entry<String, Company> entry : map.entrySet()) {
            String key = entry.getKey();
            Company value = entry.getValue();
            batch.add(new InsertOneModel(
                    new Document("code", value.getCode())
                            .append("name", value.getName())
                            .append("address", value.getAddress())
                            .append("countyCode", value.getCountyCode())
            ));
        }
        this.mongoCollection.bulkWrite(batch, new BulkWriteOptions().ordered(false));
    }

    @Override
    public void delete(String key) {
        this.mongoCollection.deleteOne(eq("_id", key));
    }

    @Override
    public void deleteAll(Collection<String> keys) {
        this.mongoCollection.deleteMany(in("_id", keys));
    }

    @Override
    public Company load(String key) {
        System.out.println("Load " + key);
        Document document = (Document) mongoCollection.find(eq("_id", key)).first();
        String code = document.getString("code");
        String name = document.getString("name");
        String address = document.getString("address");
        String countyCode = document.getString("countyCode");
        return new Company(code, name, address, countyCode);
    }

    @Override
    public Map<String, Company> loadAll(Collection<String> keys) {
        System.out.println("LoadAll " + keys);
        HashMap<String, Company> result = new HashMap<>();

        FindIterable<Document> ids = mongoCollection.find(in("_id", keys.stream().map(ObjectId::new).collect(Collectors.toList())));
        for (Document document : ids) {
            String code = document.getString("code");
            String name = document.getString("name");
            String address = document.getString("address");
            String countyCode = document.getString("countyCode");
            result.put(document.get("_id").toString(), new Company(code, name, address, countyCode));
        }
        return result;
    }

    @Override
    public Iterable<String> loadAllKeys() {
        System.out.println("LoadAllKeys");
        List<String> keys = new LinkedList<String>();
        FindIterable<Document> ids = mongoCollection.find().projection(Projections.include("_id"));
        for (Document document : ids) {
            keys.add(document.get("_id").toString());
        }
        return keys;
    }
}
