package com.ecommerce.dao;

import com.ecommerce.model.CartItem;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CartDao {
    private Connection conn;

    public CartDao(Connection conn) {
        this.conn = conn;
    }

    // Add an item to the cart
    public void addToCart(CartItem cartItem) throws SQLException {
        String sql = "INSERT INTO cart (user_id, product_id, quantity, price) VALUES (?, ?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, cartItem.getUserId());
            ps.setInt(2, cartItem.getProductId());
            ps.setInt(3, cartItem.getQuantity());
            ps.setDouble(4, cartItem.getPrice());
            ps.executeUpdate();
        }
    }

    // Get all cart items for a user
    public List<CartItem> getCartItems(int userId) throws SQLException {
        List<CartItem> cartItems = new ArrayList<>();
        String sql = "SELECT * FROM cart WHERE user_id = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                CartItem item = new CartItem(
                    rs.getInt("id"),
                    rs.getInt("user_id"),
                    rs.getInt("product_id"),
                    rs.getInt("quantity"),
                    rs.getDouble("price")
                );
                cartItems.add(item);
            }
        }
        return cartItems;
    }

    // Remove a cart item by cartItemId
    public void removeCartItem(int cartItemId) throws SQLException {
        String sql = "DELETE FROM cart WHERE id = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, cartItemId);
            ps.executeUpdate();
        }
    }

    // Clear cart for a user
    public void clearCart(int userId) throws SQLException {
        String sql = "DELETE FROM cart WHERE user_id = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, userId);
            ps.executeUpdate();
        }
    }
}
