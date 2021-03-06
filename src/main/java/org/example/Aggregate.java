package org.example;

import static org.example.CreateStudentData.REQUIRED_SUBJECTS;
import static org.example.Util.COLLECTION_NAME;
import static org.example.Util.DB_NAME;
import static org.example.Util.ENGLISH;
import static org.example.Util.HOST;
import static org.example.Util.LANGUAGE;
import static org.example.Util.MATHS;
import static org.example.Util.PORT;

import java.net.UnknownHostException;
import java.util.Date;

import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;

import com.mongodb.AggregationOutput;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;

public class Aggregate {

    public static void main(String[] args) throws UnknownHostException {

        final MongoClient mongoClient = new MongoClient(HOST, PORT);
        MongoOperations mongoOps = new MongoTemplate(mongoClient, DB_NAME);

        final DBCollection collection = mongoOps.getCollection(COLLECTION_NAME);

        System.out.println("================Find max by subject starts " + new Date()+ "=====================");
        findMaxBySubject(collection, MATHS);
        System.out.println("================Find max starts " + new Date()+ "=====================");
        findMax(collection);
        System.out.println("================Count by optional subject starts " + new Date()+ "=====================");
        countByOptionalSubject(collection, LANGUAGE);
        countByOptionalSubject(collection, ENGLISH);
        System.out.println("================Count students by city starts " + new Date()+ "=====================");
        countByCity(collection);
        //find top 10 students in Maths
        System.out.println("================Find top 10 students who scored maximum marks in maths subject starts " + new Date()+ "=====================");
        findTopNBySubject(collection, MATHS, 10);
        //find top 10 students in required subjects only, with percentage
        System.out.println("================Find top 10 students in required subjects only, with percentage starts " + new Date()+ "=====================");
        findTop10BySubjectWithPct(collection, REQUIRED_SUBJECTS);
    }

    private static void findTop10BySubjectWithPct(DBCollection collection, String[] requiredSubjects) {
        DBObject match = new BasicDBObject("$match", new BasicDBObject("subjects.name", new BasicDBObject("$in", requiredSubjects)));

        // build the $projection operation
        DBObject fields = new BasicDBObject();
        fields.put("firstName", 1);
        fields.put("lastName", 1);
        fields.put("subjects.marks", 1);
        DBObject project = new BasicDBObject("$project", fields);

        // Now the $group operation
        DBObject groupFields = new BasicDBObject( "_id", "$firstName");
        groupFields.put("total", new BasicDBObject( "$avg", "$subjects.marks"));
        DBObject group = new BasicDBObject("$group", groupFields);

        DBObject orderBy = new BasicDBObject("$sort", new BasicDBObject("total", -1));
        DBObject limit = new BasicDBObject();
        limit.put("$limit", 10);
        // run aggregation
        AggregationOutput output = collection.aggregate(new BasicDBObject("$unwind", "$subjects"), match, project, group, orderBy, limit);
        System.out.println(output.getCommand().toString());


        for (DBObject dbObject : output.results()) {
            System.out.println(dbObject);
        }
    }

    private static void countByCity(DBCollection collection) {
        // build the $projection operation
        DBObject fields = new BasicDBObject("city", 1);
        DBObject project = new BasicDBObject("$project", fields );

        // Now the $group operation
        DBObject groupFields = new BasicDBObject( "_id", "$city");
        groupFields.put("total", new BasicDBObject( "$sum", 1));
        DBObject group = new BasicDBObject("$group", groupFields);

        // run aggregation
        AggregationOutput output = collection.aggregate(project, group);
        System.out.println(output.getCommand().toString());

        for (DBObject dbObject : output.results()) {
            System.out.println(dbObject);
        }
    }

    private static void countByOptionalSubject(DBCollection collection, String subject) {
        DBObject query = new BasicDBObject("subjects.name", subject);
        System.out.println(query);
        final long count = collection.count(query);
        System.out.println(count);
    }

    private static void findMaxBySubject(DBCollection collection, String subject) {
        DBObject match = new BasicDBObject("$match", new BasicDBObject("subjects.name", subject));

        // build the $projection operation
        DBObject fields = new BasicDBObject("subjects.name", 1);
        fields.put("subjects.marks", 1);
        DBObject project = new BasicDBObject("$project", fields );

        // Now the $group operation
        DBObject groupFields = new BasicDBObject( "_id", "$subjects.name");
        groupFields.put("max", new BasicDBObject( "$max", "$subjects.marks"));
        DBObject group = new BasicDBObject("$group", groupFields);

        // run aggregation
        AggregationOutput output = collection.aggregate(new BasicDBObject("$unwind", "$subjects"), match, group);
        System.out.println(output.getCommand().toString());


        for (DBObject dbObject : output.results()) {
            System.out.println(dbObject);
        }
    }

    private static void findMax(DBCollection collection) {
        // build the $projection operation
        DBObject fields = new BasicDBObject("subjects.name", 1);
        fields.put("subjects.marks", 1);
        DBObject project = new BasicDBObject("$project", fields );

        // Now the $group operation
        DBObject groupFields = new BasicDBObject( "_id", "$subjects.name");
        groupFields.put("max", new BasicDBObject( "$max", "$subjects.marks"));
        DBObject group = new BasicDBObject("$group", groupFields);

        // run aggregation
        AggregationOutput output = collection.aggregate(new BasicDBObject("$unwind", "$subjects"), group);
        System.out.println(output.getCommand().toString());


        for (DBObject dbObject : output.results()) {
            System.out.println(dbObject);
        }
    }

    private static void findTopNBySubject(DBCollection collection, String subject, int top) {
        DBObject match = new BasicDBObject("$match", new BasicDBObject("subjects.name", subject));

        // build the $projection operation
        DBObject fields = new BasicDBObject();
        fields.put("firstName", 1);
        fields.put("lastName", 1);
        fields.put("subjects.marks", 1);
        DBObject project = new BasicDBObject("$project", fields);

        //DBObject orderBy = new BasicDBObject("$sort", "subjects.marks");
        DBObject orderBy = new BasicDBObject();
        orderBy.put("$limit", top);
        // run aggregation
        AggregationOutput output = collection.aggregate(new BasicDBObject("$unwind", "$subjects"), match, project, orderBy);
        System.out.println(output.getCommand().toString());


        for (DBObject dbObject : output.results()) {
            System.out.println(dbObject);
        }
    }
}
