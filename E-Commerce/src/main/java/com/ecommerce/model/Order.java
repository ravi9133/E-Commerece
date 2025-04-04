package com.ecommerce.model;

import java.sql.Timestamp;
import java.util.List;

public class Order {
    private int id;
    private int userId;
    private Timestamp orderDate;
    private String status;
    private List<OrderItem> orderItems;

    public Order(int id, int userId, Timestamp orderDate, String status) {
        this.id = id;
        this.userId = userId;
        this.orderDate = orderDate;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public int getUserId() {
        return userId;
    }

    public Timestamp getOrderDate() {
        return orderDate;
    }

    public String getStatus() {
        return status;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }
}
