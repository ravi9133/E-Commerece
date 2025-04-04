package com.ecommerce.dao;

import com.ecommerce.model.Product;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDao {
    private Connection conn;

    public ProductDao(Connection conn) {
        this.conn = conn;
    }

    public List<Product> getAllProducts() throws SQLException {
        List<Product> products = new ArrayList<>();
        String sql = "SELECT * FROM products";
        PreparedStatement stmt = conn.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            products.add(new Product(rs.getInt("id"), rs.getString("name"), rs.getString("description"), rs.getDouble("price"), rs.getInt("stock"), rs.getString("image_url")));
        }
        return products;
    }

    public boolean addProduct(Product product) throws SQLException {
        String sql = "INSERT INTO products (name, description, price, stock, image_url) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, product.getName());
        stmt.setString(2, product.getDescription());
        stmt.setDouble(3, product.getPrice());
        stmt.setInt(4, getStock());
        stmt.setString(4, product.getImageUrl());
        int pass = stmt.executeUpdate();
        if(pass>0)
        {
        	return true;
        }
        return false;
    }

    private int getStock() {
		// TODO Auto-generated method stub
		return 0;
	}

	public void deleteProduct(int productId) throws SQLException {
        String sql = "DELETE FROM products WHERE id = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, productId);
        stmt.executeUpdate();
    }

    public void updateProduct(Product product) throws SQLException {
        String sql = "UPDATE products SET name = ?, description = ?, price = ?, image_url = ? WHERE id = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, product.getName());
        stmt.setString(2, product.getDescription());
        stmt.setDouble(3, product.getPrice());
        stmt.setString(4, product.getImageUrl());
        stmt.setInt(5, product.getId());
        stmt.executeUpdate();
    }

    public Product getProductById(int productId) {
        Product product = null;
        String sql = "SELECT * FROM products WHERE id = ?";
        
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, productId);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                product = new Product(
                		rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("description"),
                    rs.getDouble("price"),
                    rs.getInt("stock"),
                    rs.getString("image_url")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return product;
    }

}
