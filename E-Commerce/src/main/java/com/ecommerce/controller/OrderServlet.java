package com.ecommerce.controller;

import java.io.IOException;
import java.sql.Connection;

import com.ecommerce.dao.OrderDao;
import com.ecommerce.model.User;
import com.ecommerce.util.DBConnection;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/order")
public class OrderServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        if (user == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        Connection conn = DBConnection.getConnection(); // Get DB connection
        OrderDao orderDAO = new OrderDao(conn);

        boolean success = orderDAO.placeOrder(user.getId());

        if (success) {
            response.sendRedirect("orders.jsp");
        } else {
            request.setAttribute("errorMessage", "Failed to place order!");
            request.getRequestDispatcher("cart.jsp").forward(request, response);
        }
    }
}
