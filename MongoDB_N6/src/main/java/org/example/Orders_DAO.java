package org.example;

import com.mongodb.MongoException;
import com.mongodb.client.AggregateIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.*;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import org.bson.Document;
import org.bson.conversions.Bson;

import javax.swing.*;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Orders_DAO {

    private static MongoCollection<Document> collection;

    public Orders_DAO(DBConnect dbConnect) {
        this.collection = dbConnect.getOrdersCollection(); // Lấy collection từ DBConnect
    }

    // Đổi ngày từ String -> LocalDateTime
    private LocalDateTime parseDateTime(String dateTimeStr) {
        if (dateTimeStr == null) return null;
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            return LocalDateTime.parse(dateTimeStr, formatter);
        } catch (Exception e) {
            System.err.println("Lỗi chuyển đổi: " + dateTimeStr + e.getMessage());
            return null;
        }
    }


    //Lấy tất cả document ở DB và chuyển thành list các đối tượng
    public ArrayList<Orders> getAllOders() {
        ArrayList<Orders> list = new ArrayList<>();
        for (Document doc : collection.find()) {
            list.add(new Orders(
                    doc.getString("order_id"),
                    doc.getString("customer_id"),
                    doc.getString("customer_name"),
                    parseDateTime(doc.getString("order_date")),
                    parseDateTime(doc.getString("checkin_date")),
                    parseDateTime(doc.getString("checkout_date")),
                    doc.getInteger("tax"),
                    doc.getString("room_code"),
                    ((Number) doc.get("unit_price")).doubleValue(),
                    doc.getInteger("discount"),
                    ((Number) doc.get("total_price")).doubleValue(),
                    parseServices(doc.getList("service", String.class))
            ));
        }
        return list;
    }

    //Chuyển danh sách dịch vụ từ DB thành list<String>
    private List<String> parseServices(List<String> services) {
        return services != null ? services : new ArrayList<>();
    }

    public boolean isOrderCodeExists(String orderCode) {
        Document doc = collection.find(Filters.eq("order_id", orderCode)).first();
        return doc != null;
    }

    public void addOrder(Orders order) {
        try {
            String strOrderDate = order.getOrderDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            String strCheckinDate = order.getCheckinDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            String strCheckoutDate = order.getCheckoutDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

            Document doc = new Document("order_id", order.getOrderID())
                    .append("customer_id", order.getCustomerID())
                    .append("customer_name", order.getCustomerName())
                    .append("order_date", strOrderDate)
                    .append("checkin_date", strCheckinDate)
                    .append("checkout_date", strCheckoutDate)
                    .append("tax", order.getTax())
                    .append("room_code", order.getRoomCode())
                    .append("unit_price", order.getUnitPrice())
                    .append("discount", order.getDiscount())
                    .append("total_price", order.getTotalPrice())
                    .append("service", order.getService());
            collection.insertOne(doc);
        } catch (MongoException e) {
            throw new MongoException("Lỗi thêm order: " + e.getMessage());
        }
    }

    //Update hoá đơn theo order_id
    public boolean updateOrder(String orderID, Orders updatedOrder) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        Document query = new Document("order_id", orderID);
        Document existingOrder = collection.find(query).first();//tìm order
        String existingCustomerID = existingOrder.getString("customer_id");//Đọc customer_id cũ từ database đảm bảo customer_id ko bị thay đổi
        Document update = new Document("$set", new Document()
                .append("customer_name", updatedOrder.getCustomerName())
                .append("order_date", updatedOrder.getOrderDate().format(formatter))
                .append("checkin_date", updatedOrder.getCheckinDate().format(formatter))
                .append("checkout_date", updatedOrder.getCheckoutDate().format(formatter))
                .append("tax", updatedOrder.getTax())
                .append("room_code", updatedOrder.getRoomCode())
                .append("unit_price", updatedOrder.getUnitPrice())
                .append("discount", updatedOrder.getDiscount())
                .append("total_price", updatedOrder.getTotalPrice())
                .append("service", updatedOrder.getService())
                .append("customer_id", existingCustomerID));


        UpdateResult res = collection.updateOne(query, update);// Cập nhật và trả về kết quả các document được cập nhật
        return res.getModifiedCount() > 0; //Nếu kết quả cập nhật > 0 -> true else -> false
    }

    //Delete hoá đơn theo order_id
    public boolean deleteOrder(String orderID) {
        Document query = new Document("order_id", orderID);
        DeleteResult res = collection.deleteOne(query);// Xoá và trả về kết quả các document bị xoá
        return res.getDeletedCount() > 0;// số document bị xoá > 0 -> true else -> false
    }

    //Tìm hoá đơn theo orderID
    public Orders findOderByOrderID(String orderID) {
        List<Bson> pipeline = Arrays.asList(
                Aggregates.match(Filters.eq("order_id",orderID))
        );
        AggregateIterable<Document> aggregate = collection.aggregate(pipeline);
        Document doc = aggregate.first();

        if (doc != null) {
            return new Orders(
                doc.getString("order_id"),
                doc.getString("customer_id"),
                doc.getString("customer_name"),
                parseDateTime(doc.getString("order_date")),
                parseDateTime(doc.getString("checkin_date")),
                parseDateTime(doc.getString("checkout_date")),
                doc.getInteger("tax"),
                doc.getString("room_code"),
                doc.getDouble("unit_price"),
                doc.getInteger("discount"),
                doc.getDouble("total_price"),
                parseServices(doc.getList("service", String.class))
            );
        } else {
            return null;
        }
    }

    //Sắp xếp theo số ngày lập hoá đơn gần nhất
    public List<Document> sortOrdersByOrderDate() {
        List<Bson> pipeline = Arrays.asList(
          Aggregates.sort(Sorts.descending("order_date"))
        );
        AggregateIterable<Document> aggregate = collection.aggregate(pipeline);
        List<Document> resSort = new ArrayList<>();
        aggregate.forEach(resSort::add);
        return resSort;
    }
}
