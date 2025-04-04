package com.ecommerce.controller;

import java.io.IOException;
import java.sql.Connection;

import com.ecommerce.dao.UserDao;
import com.ecommerce.model.User;
import com.ecommerce.util.DBConnection;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String role = request.getParameter("role");

        Connection conn = DBConnection.getConnection();
        UserDao userDAO = new UserDao(conn);

        // Check if user already exists
        if (userDAO.userExists(email)) {
            request.setAttribute("errorMessage", "User already exists!");
            request.getRequestDispatcher("register.jsp").forward(request, response);
            return;
        }

     // Default role to "user" if not provided (preventing null)
        if (role == null || role.trim().isEmpty()) {
            role = "user";
        }

        // Create a user object with the selected role
        User user = new User(0, username, email, password, role);

        if (userDAO.registerUser(user)) {
            response.sendRedirect("login.jsp");
        } else {
            request.setAttribute("errorMessage", "Registration failed!");
            request.getRequestDispatcher("register.jsp").forward(request, response);
        }
    }
}

