package connectDB;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;

public class ConnectDB {
    private static MongoClient mongoClient = null;
    private static ConnectDB instance = new ConnectDB();

    // Connection String và tên database
    private static final String CONNECTION_STRING = "mongodb://localhost:27017";
    private static final String DATABASE_NAME = "Khachsan";

    // Singleton instance
    public static ConnectDB getInstance() {
        return instance;
    }

    // Kết nối tới MongoDB
    public MongoDatabase connect() {
        try {
            if (mongoClient == null) {
                mongoClient = MongoClients.create(CONNECTION_STRING);
                System.out.println("Kết nối tới MongoDB thành công!");
            }
            // Trả về database
            return mongoClient.getDatabase(DATABASE_NAME);
        } catch (Exception e) {
            System.err.println("Lỗi khi kết nối MongoDB: " + e.getMessage());
            throw new RuntimeException("Không thể kết nối tới MongoDB");
        }
    }

    // Ngắt kết nối MongoDB
    public void disconnect() {
        if (mongoClient != null) {
            try {
                mongoClient.close();
                mongoClient = null;
                System.out.println("Kết nối MongoDB đã đóng.");
            } catch (Exception e) {
                System.err.println("Lỗi khi đóng kết nối MongoDB: " + e.getMessage());
            }
        }
    }

    // Lấy MongoDatabase instance
    public static MongoDatabase getDatabase() {
        if (mongoClient == null) {
            instance.connect();
        }
        return mongoClient.getDatabase(DATABASE_NAME);
    }
}
