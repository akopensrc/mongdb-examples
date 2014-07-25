package org.example;

import static org.example.Util.AHMEDABAD;
import static org.example.Util.BARODA;
import static org.example.Util.BHOPAL;
import static org.example.Util.CHENNAI;
import static org.example.Util.DELHI;
import static org.example.Util.HOST;
import static org.example.Util.JAIPUR;
import static org.example.Util.MUMBAI;
import static org.example.Util.NOIDA;
import static org.example.Util.PORT;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Random;

import org.apache.commons.math3.random.RandomDataGenerator;
import org.example.domain.Purchase;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;

import com.mongodb.MongoClient;

/*
//Find total purchases by name
db.purchase.mapReduce(function(){
    emit(this.name, {name:this.name, amount:this.amount, count:1});
},
function(key, values) {
    var reducedObject = {
        name: key,
        amount: 0,
        count:0
    };
    values.forEach( function(value) {
        reducedObject.amount += value.amount;
        reducedObject.count += value.count;
    }
    );
    return reducedObject;
    //return {"number": Array.sum(values), "total": Array.sum(values)}
    //return Array.sum(values)
},
{
    out: "total"
});
 */
public class CreatePurchaseData {


    public static void main(String[] args) throws UnknownHostException {

        final RandomDataGenerator randomDataGenerator = new RandomDataGenerator();

        String[] customers = {"David", "Charles", "Martin", "Robert"};
        String[] cities = {AHMEDABAD, MUMBAI, DELHI, CHENNAI, JAIPUR, NOIDA, BARODA, BHOPAL};

        final MongoClient mongoClient = new MongoClient(HOST, PORT);
        MongoOperations mongoOps = new MongoTemplate(mongoClient, "purchase");
        mongoOps.dropCollection(Purchase.class);

        final ArrayList<Purchase> purchases = new ArrayList<Purchase>();
        for(int i=1; i<=1000; i++) {

            final int customerId = randomDataGenerator.nextInt(0, customers.length - 1);
            final double amount = getRandomDouble(1, 5000);
            final int cityId = randomDataGenerator.nextInt(0, cities.length - 1);
            purchases.add(new Purchase(customers[customerId], amount, cities[cityId]));
        }
        mongoOps.insert(purchases, Purchase.class);
    }

    public static double getRandomDouble(int start, int end) {
        final double random = new Random().nextDouble();
        double result = start + (random * (end - start));

        return result;
    }
}
