package com.ecommerce.controller;

import java.io.IOException;
import java.sql.Connection;

import com.ecommerce.dao.ProductDao;
import com.ecommerce.model.Product;
import com.ecommerce.util.DBConnection;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/addProduct")
public class AddProductServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve product details from form
    	int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        double price = Double.parseDouble(request.getParameter("price"));
        int stock = Integer.parseInt(request.getParameter("stock"));
        String imageUrl = request.getParameter("imageUrl");

        // Create Product object
        Product product = new Product( id, name, description, price, stock, imageUrl);

        try {
        	Connection conn = DBConnection.getConnection();
        	ProductDao productDAO = new ProductDao(conn);

            // Add product to database
            boolean success = productDAO.addProduct(product);

            if (success) {
                response.sendRedirect("admin.jsp?message=Product added successfully");
            } else {
                response.sendRedirect("admin.jsp?error=Failed to add product");
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("admin.jsp?error=Database error");
        }
    }
}
