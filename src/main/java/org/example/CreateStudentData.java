package org.example;

import static org.example.Util.AHMEDABAD;
import static org.example.Util.BARODA;
import static org.example.Util.BHOPAL;
import static org.example.Util.BIOLOGY;
import static org.example.Util.CHEMISTRY;
import static org.example.Util.CHENNAI;
import static org.example.Util.COLLECTION_NAME;
import static org.example.Util.DELHI;
import static org.example.Util.ENGLISH;
import static org.example.Util.HOST;
import static org.example.Util.JAIPUR;
import static org.example.Util.LANGUAGE;
import static org.example.Util.MATHS;
import static org.example.Util.MUMBAI;
import static org.example.Util.NOIDA;
import static org.example.Util.PHYSICS;
import static org.example.Util.PORT;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.mongodb.DBCollection;
import org.apache.commons.math3.random.RandomDataGenerator;
import org.example.domain.Student;
import org.example.domain.Subject;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;

import com.mongodb.MongoClient;

public class CreateStudentData {


    public static void main(String[] args) throws UnknownHostException {

        final RandomDataGenerator randomDataGenerator = new RandomDataGenerator();

        String[] requiredSubjects = {MATHS, CHEMISTRY, PHYSICS, BIOLOGY};
        String[] optionalSubjects = {LANGUAGE, ENGLISH};
        String[] cities = {AHMEDABAD, MUMBAI, DELHI, CHENNAI, JAIPUR, NOIDA, BARODA, BHOPAL};

        final MongoClient mongoClient = new MongoClient(HOST, PORT);
        MongoOperations mongoOps = new MongoTemplate(mongoClient, COLLECTION_NAME);
        mongoOps.dropCollection(Student.class);

        int counter = 1;
        final ArrayList<Student> students = new ArrayList<Student>();
        for(int i=1; i<=50000; i++) {
            final String first = "First_" + i;
            final String last = "Last_" + i;
            final String username = "username_" + i;
            final String pwd = "password_" + i;

            final List<Subject> subjects = new ArrayList<Subject>();
            for (String requiredSubject : requiredSubjects) {
                final int marks = randomDataGenerator.nextInt(1, 100);
                subjects.add(new Subject(requiredSubject, marks));
            }

            final int subjectId = randomDataGenerator.nextInt(0, 1);
            final String optionalSubject = optionalSubjects[subjectId];
            final int marks = randomDataGenerator.nextInt(1, 100);
            subjects.add(new Subject(optionalSubject, marks));

            final int cityId = randomDataGenerator.nextInt(0, cities.length-1);
            students.add(new Student(first, last, username, pwd, subjects, cities[cityId]));

            if(students.size() == 10000) {
                mongoOps.insert(students, Student.class);
                System.out.println("Batch " + counter++ + " inserted. " + new Date());
                students.clear();
            }
        }
    }
}
