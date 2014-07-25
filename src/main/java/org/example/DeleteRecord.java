package org.example;

import static org.example.Util.AHMEDABAD;
import static org.example.Util.COLLECTION_NAME;
import static org.example.Util.DB_NAME;
import static org.example.Util.HOST;
import static org.example.Util.PORT;
import static org.example.Util.printRecords;

import java.net.UnknownHostException;

import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;

public class DeleteRecord {
    public static void main(String[] args) throws UnknownHostException {
        final MongoClient mongoClient = new MongoClient(HOST, PORT);
        MongoOperations mongoOps = new MongoTemplate(mongoClient, DB_NAME);

        final DBCollection collection = mongoOps.getCollection(COLLECTION_NAME);

        final BasicDBObject query = new BasicDBObject("city", AHMEDABAD);
        printRecords(collection.find(query));
        System.out.println("=================================================");

        collection.remove(query);

        printRecords(collection.find(query));

    }
}
