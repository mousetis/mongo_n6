package org.example;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.IndexOptions;
import com.mongodb.client.model.Indexes;
import org.bson.Document;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class DBConnect {
    private MongoClient mongoClient;
    private static MongoCollection<Document> collection;
    private static MongoDatabase database;

    //Connect MongoDB
    public DBConnect() {
        database = null;
        try {
            mongoClient = MongoClients.create("mongodb+srv://chitoanphan8505:Phanchitoan8505%40@nhom6.bziftgg.mongodb.net/");
            database = mongoClient.getDatabase("ChiToan_23714711");
            collection = database.getCollection("Orders");
            System.out.println("Connected to database: " + database.getName());
        } catch (Exception e) {
            throw new RuntimeException("Error connecting to MongoDB: " + database.getName());
        }
    }

    //phương thức lấy collection từ database
    public MongoCollection<Document> getOrdersCollection() {
        return database.getCollection("Orders");
    }

    // đóng kết nối
    public void close() {
        mongoClient.close();
    }
}
