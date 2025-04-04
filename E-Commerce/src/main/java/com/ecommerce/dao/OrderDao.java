package com.ecommerce.dao;

import com.ecommerce.model.Order;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderDao {
    private Connection conn;

    public OrderDao(Connection conn) {
        this.conn = conn;
    }

    // ✅ Fixed: Returns boolean & handles cart items (TODO: Implement CartDAO)
    public boolean placeOrder(int userId) {
        String sql = "INSERT INTO orders (user_id, status) VALUES (?, 'PENDING')";
        try (PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setInt(1, userId);
            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected > 0) {
                ResultSet rs = stmt.getGeneratedKeys();
                if (rs.next()) {
                    int orderId = rs.getInt(1);
                    // TODO: Move cart items to order_items (handled in CartDAO)
                    return true; // Order placed successfully
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false; // Order failed
    }

    // ✅ Fixed: Uses try-with-resources
    public List<Order> getUserOrders(int userId) {
        List<Order> orders = new ArrayList<>();
        String sql = "SELECT * FROM orders WHERE user_id = ?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, userId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    orders.add(new Order(
                        rs.getInt("id"),
                        userId,
                        rs.getTimestamp("order_date"),
                        rs.getString("status")
                    ));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orders;
    }

    // ✅ Fixed: Uses try-with-resources
    public boolean updateOrderStatus(int orderId, String status) {
        String sql = "UPDATE orders SET status = ? WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, status);
            stmt.setInt(2, orderId);
            int rowsUpdated = stmt.executeUpdate();
            return rowsUpdated > 0; // Return true if update was successful
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
