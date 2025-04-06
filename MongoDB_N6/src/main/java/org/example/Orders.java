package org.example;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

public class Orders {
    private String orderID;
    private String customerID;
    private String customerName;
    private LocalDateTime orderDate;
    private LocalDateTime checkinDate;
    private LocalDateTime checkoutDate;
    private int tax;
    private String roomCode;
    private Double unitPrice;
    private int discount;
    private Double totalPrice;
    private List<String> service;

    public Orders() {
    }

    public Orders(String orderID, String customerID, String customerName, LocalDateTime orderDate, LocalDateTime checkinDate, LocalDateTime checkoutDate, int tax, String roomCode, Double unitPrice, int discount, Double totalPrice, List<String> service) {
        this.orderID = orderID;
        this.customerID = customerID;
        this.customerName = customerName;
        this.orderDate = orderDate;
        this.checkinDate = checkinDate;
        this.checkoutDate = checkoutDate;
        this.tax = tax;
        this.roomCode = roomCode;
        this.unitPrice = unitPrice;
        this.discount = discount;
        this.totalPrice = totalPrice;
        this.service = service;
    }

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }

    public LocalDateTime getCheckinDate() {
        return checkinDate;
    }

    public void setCheckinDate(LocalDateTime checkinDate) {
        this.checkinDate = checkinDate;
    }

    public LocalDateTime getCheckoutDate() {
        return checkoutDate;
    }

    public void setCheckoutDate(LocalDateTime checkoutDate) {
        this.checkoutDate = checkoutDate;
    }

    public int getTax() {
        return tax;
    }

    public void setTax(int tax) {
        this.tax = tax;
    }

    public String getRoomCode() {
        return roomCode;
    }

    public void setRoomCode(String roomCode) {
        this.roomCode = roomCode;
    }

    public Double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public List<String> getService() { return service; }

    public void setService(List<String> service) { this.service = service; }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Orders orders)) return false;
        return Objects.equals(orderID, orders.orderID) && Objects.equals(customerID, orders.customerID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderID, customerID);
    }

    @Override
    public String toString() {
        return "Oders{" +
                "orderID='" + orderID + '\'' +
                ", customerID='" + customerID + '\'' +
                ", customerName='" + customerName + '\'' +
                ", orderDate=" + orderDate +
                ", checkinDate=" + checkinDate +
                ", checkoutDate=" + checkoutDate +
                ", tax=" + tax +
                ", roomCode='" + roomCode + '\'' +
                ", unitPrice=" + unitPrice +
                ", discount=" + discount +
                ", totalPrice=" + totalPrice +
                ", service=" + service +
                '}';
    }
}
