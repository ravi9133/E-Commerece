package com.ecommerce.model;

public class CartItem {
    private int id;  // Auto-incremented cart item ID
    private int userId;
    private int productId;
    private int quantity;
    private double price;

    public CartItem() {}

    public CartItem(int userId, int productId, int quantity, double price) {
        this.userId = userId;
        this.productId = productId;
        this.quantity = quantity;
        this.price = price;
    }

    public CartItem(int id, int userId, int productId, int quantity, double price) {
        this.id = id;
        this.userId = userId;
        this.productId = productId;
        this.quantity = quantity;
        this.price = price;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
