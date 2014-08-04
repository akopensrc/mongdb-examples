package org.example;

import static org.example.Util.HOST;
import static org.example.Util.MUMBAI;
import static org.example.Util.NEW_JERSEY;
import static org.example.Util.PORT;

import java.net.UnknownHostException;

import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;


public class UpdatePurchaseCity {

    public static void main(String[] args) throws UnknownHostException {

        final MongoClient mongoClient = new MongoClient(HOST, PORT);
        MongoOperations mongoOps = new MongoTemplate(mongoClient, "purchase");
        final DBCollection purchase = mongoOps.getCollection("purchase");

        final BasicDBObject query = new BasicDBObject("city", NEW_JERSEY);
        final BasicDBObject update = new BasicDBObject("$set", new BasicDBObject("city", MUMBAI));
        purchase.update(query, update, false, true);

    }
}
