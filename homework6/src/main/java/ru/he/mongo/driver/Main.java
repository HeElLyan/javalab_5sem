package ru.he.mongo.driver;

import com.mongodb.BasicDBObject;
import com.mongodb.client.*;
import org.bson.Document;

import java.util.Arrays;

import static com.mongodb.client.model.Projections.excludeId;
import static com.mongodb.client.model.Projections.fields;
import static com.mongodb.client.model.Projections.include;

public class Main {
    public static void main(String[] args) {
        MongoClient client = MongoClients.create();
        MongoDatabase database = client.getDatabase("metal");
        MongoCollection<Document> collection1 = database.getCollection("bands");
        MongoCollection<Document> collection2 = database.getCollection("concerts");

//        //insert
//        Document periphery = new Document()
//                .append("title", "Periphery")
//                .append("fansCount", 20000000)
//                .append("albumsCount", 9)
//                .append("genres", Arrays.asList("progressive metal", "djent"))
//                .append("active", true)
//                .append("keywords", Arrays.asList("progressive metal", "djent", "extreme vocal"))
//                .append("description", "My lovely prog band");
//
//
//        Document architects = new Document()
//                .append("title", "Architects")
//                .append("fansCount", 100000000)
//                .append("albumsCount", 2)
//                .append("genres", Arrays.asList("progressive metal", "metalcore"))
//                .append("active", true)
//                .append("keywords", Arrays.asList("progressive metal", "metalcore", "extreme vocal"))
//                .append("members", Arrays.asList("sam", "phil", "matthew"));
//
//        Document agonist = new Document()
//                .append("title", "The agonist")
//                .append("albumsCount", 6)
//                .append("keywords", Arrays.asList("mdm", "extreme vocal"))
//                .append("rating", 8);
//
//        Document bmth = new Document()
//                .append("title", "Bring Me The Horizon")
//                .append("fansCount", 1000000000)
//                .append("albumsCount", 12)
//                .append("members", Arrays.asList("oliver", "phil", "matthew"));
//
//        Document noname = new Document()
//                .append("title", "Djent")
//                .append("state", "draft")
//                .append("active", false)
//                .append("albumsCount", 12)
//                .append("keywords", Arrays.asList("progressive metal", "metalcore", "extreme vocal"));
//
//        collection1.insertMany(Arrays.asList(periphery, architects, agonist, bmth, noname));
//
//        Document concert1 = new Document()
//                .append("title", "HellFest")
//                .append("country", "USA")
//                .append("genres", Arrays.asList("progressive metal", "metalcore", "mdm", "death metal"));
//
//        Document concert2 = new Document()
//                .append("title", "Wacken Open Air")
//                .append("country", "USA")
//                .append("genres", Arrays.asList("progressive metal", "metalcore", "mdm", "death metal"));
//
//        collection2.insertMany(Arrays.asList(concert1, concert2));


        //update1
        BasicDBObject query = new BasicDBObject();
        query.put("title", "Periphery"); // (1)

        BasicDBObject newDocument = new BasicDBObject();
        newDocument.put("title", "Periphery!!!"); // (2)

        BasicDBObject updateObject = new BasicDBObject();
        updateObject.put("$set", newDocument); // (3)

        database.getCollection("bands").updateOne(query, updateObject);

        //update2
        BasicDBObject query2 =
                new BasicDBObject().append("$inc",
                        new BasicDBObject().append("albumsCount", 1));

        database.getCollection("bands").updateOne(new BasicDBObject().append("title", "Periphery!!!"), query2);

        //update3
        BasicDBObject searchQuery = new BasicDBObject();
        searchQuery.append("country", "USA");

        BasicDBObject updateQuery = new BasicDBObject();
        updateQuery.append("$set",
                new BasicDBObject().append("keywords", Arrays.asList("fun", "metal", "mosh")));

        database.getCollection("concerts").updateMany(searchQuery, updateQuery);

        //find bands
        Document searchPeriphery = new Document()
                .append("title", "Periphery!!!");

        FindIterable<Document> resultDocuments = collection1.find(searchPeriphery)
                .projection(fields(include("title", "fansCount", "albumsCount", "genres"), excludeId()));

        for (Document document : resultDocuments) {
            System.out.println(document.toJson());
        }

        //find concerts
        Document searchConcert = new Document()
                .append("title", "Wacken Open Air");

        FindIterable<Document> resultDocuments2 = collection2.find(searchConcert)
                .projection(fields(include("title", "country", "genres", "keywords"), excludeId()));

        for (Document document : resultDocuments2) {
            System.out.println(document.toJson());
        }

        collection1.drop();
        collection2.drop();

    }
}
