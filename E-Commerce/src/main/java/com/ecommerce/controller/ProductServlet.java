package com.ecommerce.controller;

import com.ecommerce.dao.ProductDao;
import com.ecommerce.model.Product;
import com.ecommerce.util.DBConnection;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

@WebServlet("/ProductServlet")
public class ProductServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private ProductDao productDAO;

    @Override
    public void init() {
        Connection conn = DBConnection.getConnection();
        productDAO = new ProductDao(conn);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        try {
            if ("add".equals(action)) {
                String name = request.getParameter("name");
                String description = request.getParameter("description");
                double price = Double.parseDouble(request.getParameter("price"));
                int stock = Integer.parseInt(request.getParameter("stock"));
                String imageUrl = request.getParameter("imageUrl");

                Product product = new Product(name, description, price, stock, imageUrl);
                productDAO.addProduct(product);
                response.sendRedirect("admin.jsp");

            } else if ("update".equals(action)) {
                int id = Integer.parseInt(request.getParameter("id"));
                String name = request.getParameter("name");
                String description = request.getParameter("description");
                double price = Double.parseDouble(request.getParameter("price"));
                int stock = Integer.parseInt(request.getParameter("stock"));
                String imageUrl = request.getParameter("imageUrl");

                Product product = new Product(id, name, description, price, stock, imageUrl);
                productDAO.updateProduct(product);
                response.getWriter().write("Success");

            } else if ("delete".equals(action)) {
                int id = Integer.parseInt(request.getParameter("id"));
                productDAO.deleteProduct(id);
                response.sendRedirect("admin.jsp");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp");
        }
    }
}
