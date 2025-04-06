package dao;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import connectDB.ConnectDB;
import entity.Room;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

public class RoomDAO {
    private MongoCollection<Document> roomCollection;

    public RoomDAO() {
        MongoDatabase database = ConnectDB.getInstance().connect();
        this.roomCollection = database.getCollection("rooms"); 
    }

    // Thêm phòng
    public boolean addRoom(Room room) {
        try {
            Document doc = new Document("RoomCode", room.getRoomCode())
                    .append("RoomName", room.getRoomName())
                    .append("RoomType", room.getRoomType())
                    .append("RoomPrice", room.getRoomPrice())
                    .append("RoomStatus", room.getRoomStatus())
                    .append("MaxPeople", room.getMaxPeople());
            roomCollection.insertOne(doc);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // Xóa phòng
    public boolean removeRoom(String roomCode) {
        try {
            roomCollection.deleteOne(Filters.eq("RoomCode", roomCode));
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // Tìm phòng
    public Room findRoom(String roomCode) {
        try {
            Document doc = roomCollection.find(Filters.eq("RoomCode", roomCode)).first();
            if (doc != null) {
                return new Room(
                        doc.getString("RoomCode"),
                        doc.getString("RoomName"),
                        doc.getString("RoomType"),
                        doc.getInteger("RoomPrice"),
                        doc.getString("RoomStatus"),
                        doc.getInteger("MaxPeople")
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    // Lấy danh sách phòng
 // Lấy danh sách phòng
    public List<Room> getRoomList() {
        List<Room> rooms = new ArrayList<>();
        try {
            for (Document doc : roomCollection.find()) { // Sử dụng this.roomCollection
                String roomCode = doc.getString("RoomCode"); // Có thể null, nếu cần bạn kiểm tra
                String roomName = doc.getString("RoomName");
                String roomType = doc.getString("RoomType");
                Integer roomPrice = doc.getInteger("RoomPrice", 0);
                String roomStatus = doc.getString("RoomStatus");
                Integer maxPeople = doc.getInteger("MaxPeople", 0);

                // Tạo đối tượng Room
                Room room = new Room(roomCode, roomName, roomType, roomPrice, roomStatus, maxPeople);
                rooms.add(room);
            }
        } catch (Exception e) {
            e.printStackTrace(); // In lỗi ra console để debug
        }
        System.out.println("Rooms loaded: " + rooms.size());
        return rooms;
    }


    // Cập nhật thông tin phòng
    public boolean updateRoom(String roomCode, Room updatedRoom) {
        try {
            Document updateDoc = new Document("$set", new Document("RoomName", updatedRoom.getRoomName())
                    .append("RoomType", updatedRoom.getRoomType())
                    .append("RoomPrice", updatedRoom.getRoomPrice())
                    .append("RoomStatus", updatedRoom.getRoomStatus())
                    .append("MaxPeople", updatedRoom.getMaxPeople()));
            roomCollection.updateOne(Filters.eq("RoomCode", roomCode), updateDoc);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}

