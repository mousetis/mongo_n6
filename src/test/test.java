package test;

import com.mongodb.client.*;
import org.bson.Document;

public class test {
    public static void main(String[] args) {
        String connectionString = "mongodb://localhost:27017";
        try (MongoClient mongoClient = MongoClients.create(connectionString)) {
            MongoDatabase database = mongoClient.getDatabase("Khachsan");
            MongoCollection<Document> collection = database.getCollection("rooms");

            for (Document doc : collection.find()) {
                System.out.println(doc.toJson());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
