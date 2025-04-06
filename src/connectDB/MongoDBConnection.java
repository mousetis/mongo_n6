package connectDB;

import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoCollection;
import org.bson.Document;


public class MongoDBConnection {
    private static final String URI = "mongodb://localhost:27017"; // Thay đổi nếu dùng cloud
    private static final String DATABASE_NAME = "hotel";
    private static final String COLLECTION_NAME = "nhanVien";


    private MongoClient mongoClient;
    private MongoDatabase database;
    private MongoCollection<Document> collection;

    public MongoDBConnection() {
        mongoClient = MongoClients.create(URI);
        database = mongoClient.getDatabase(DATABASE_NAME);
        collection = database.getCollection(COLLECTION_NAME);

    }
    
    public MongoDBConnection(String url, String dbName, String collectionName) {
    	mongoClient = MongoClients.create(url);
        database = mongoClient.getDatabase(dbName);
        collection = database.getCollection(collectionName);
    }

    public MongoCollection<Document> getCollection() {
        return collection;
    }

    public void close() {
        mongoClient.close();

    }
}