package org.example;

import static org.example.Util.AHMEDABAD;
import static org.example.Util.COLLECTION_NAME;
import static org.example.Util.DB_NAME;
import static org.example.Util.HOST;
import static org.example.Util.PORT;

import java.net.UnknownHostException;
import java.util.Date;

import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;

public class FindAndModify {
    public static void main(String[] args) throws UnknownHostException {
        final MongoClient mongoClient = new MongoClient(HOST, PORT);
        MongoOperations mongoOps = new MongoTemplate(mongoClient, DB_NAME);

        final DBCollection collection = mongoOps.getCollection(COLLECTION_NAME);

        final BasicDBObject query = new BasicDBObject("city", AHMEDABAD);
        System.out.println(new Date());
        System.out.println(collection.count(query));
        System.out.println(new Date());
        System.out.println("=================================================");
        final BasicDBObject update = new BasicDBObject("$set", new BasicDBObject("active", true));
        collection.update(query, update, false, true);

        System.out.println(collection.count(new BasicDBObject("active", true)));

    }
}
