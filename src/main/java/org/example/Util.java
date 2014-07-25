package org.example;

import com.mongodb.DBCursor;

public class Util {

    public static final String HOST = "localhost";
    public static final int PORT = 27019;

    public static final String BIOLOGY = "Biology";
    public static final String LANGUAGE = "Language";
    public static final String ENGLISH = "English";
    public static final String MUMBAI = "Mumbai";
    public static final String DELHI = "Delhi";
    public static final String CHENNAI = "Chennai";
    public static final String JAIPUR = "Jaipur";
    public static final String NOIDA = "Noida";
    public static final String BARODA = "Baroda";
    public static final String BHOPAL = "Bhopal";
    public static final String AHMEDABAD = "Ahmedabad";
    public static final String DB_NAME = "student";
    public static final String COLLECTION_NAME = "student";
    public static final String MATHS = "Maths";
    public static final String CHEMISTRY = "Chemistry";
    public static final String PHYSICS = "Physics";

    public static void printRecords(DBCursor cursor) {
        while(cursor.hasNext()) {
            System.out.println(cursor.next());
        }
    }
}
