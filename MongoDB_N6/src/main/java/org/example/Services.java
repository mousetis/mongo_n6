package org.example;

import org.bson.Document;

import java.util.List;

public class Services {

    private Orders_DAO orders_dao;

    //Constructor khởi tạo Services với Orders_DAO
    public Services(DBConnect dbConnect) {
        this.orders_dao = new Orders_DAO(dbConnect);
    }

    //Lấy tất cả các Order từ DAO
    public List<Orders> getAllOrders() {
        return orders_dao.getAllOders();
    }

    //Thêm 1 Order
    public void addOrder(Orders order) throws Exception {
        if(orders_dao.isOrderCodeExists(order.getOrderID())) {
            throw new Exception("Mã hoá đơn bị trùng");
        }
        else{
            orders_dao.addOrder(order);
        }
    }

    //Xoá 1 Order
    public boolean deleteOrder(String orderID) {
        return orders_dao.deleteOrder(orderID);
    }

    //Update 1 Order
    public boolean updateOrder(String orderID, Orders order) {
        return orders_dao.updateOrder(orderID, order);
    }

    //Find Order theo OrderID
    public Orders findOderByOrderID(String orderID) {
        return orders_dao.findOderByOrderID(orderID);
    }

    //Sort Order theo ngày gần nhất
    public List<Document> sortByOrdersDate() {
        return orders_dao.sortOrdersByOrderDate();
    }
}
