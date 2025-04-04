package com.ecommerce.controller;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

import com.ecommerce.dao.OrderDao;
import com.ecommerce.dao.ProductDao;
import com.ecommerce.model.Order;
import com.ecommerce.model.Product;
import com.ecommerce.model.User;
import com.ecommerce.util.DBConnection;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/home")
public class HomeServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private ProductDao productDao;
    private OrderDao orderDao;

    public void init() {
        Connection conn = DBConnection.getConnection();
        productDao = new ProductDao(conn);
        orderDao = new OrderDao(conn);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            response.sendRedirect("login.jsp");
            return;
        }
        
        try {
            List<Product> productList = productDao.getAllProducts();
            request.setAttribute("productList", productList);
        
            if ("user".equals(user.getRole())) {
                // Fetch orders for the logged-in user
                List<Order> orderList = orderDao.getUserOrders(user.getId());
                request.setAttribute("orderList", orderList);
            }
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "Error loading products.");
        }
        
        request.getRequestDispatcher("display.jsp").forward(request, response);
    }
}
