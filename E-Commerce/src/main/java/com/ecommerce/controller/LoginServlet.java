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
import jakarta.servlet.http.HttpSession;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        Connection conn = DBConnection.getConnection();
        UserDao userDAO = new UserDao(conn);
        
        User user = userDAO.validateUser(email, password);

        if (user != null) {
            HttpSession session = request.getSession();
            session.setAttribute("user", user);
            response.sendRedirect("home.jsp");
        } else {
            request.setAttribute("errorMessage", "Invalid email or password!");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }
}
