package com.ecommerce.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.ecommerce.dao.CartDao;
import com.ecommerce.dao.ProductDao;
import com.ecommerce.model.CartItem;
import com.ecommerce.model.Product;
import com.ecommerce.model.User;
import com.ecommerce.util.DBConnection;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/cart")
public class CartServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private CartDao cartDAO;
    private ProductDao productDAO;

    @Override
    public void init() {
        Connection conn = DBConnection.getConnection();
        cartDAO = new CartDao(conn);
        productDAO = new ProductDao(conn);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        if (user == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        String action = request.getParameter("action");

        try {
            if ("add".equals(action)) {
                int productId = Integer.parseInt(request.getParameter("productId"));
                int quantity = Integer.parseInt(request.getParameter("quantity"));

                if (quantity <= 0) {
                    session.setAttribute("error", "Quantity must be at least 1.");
                    response.sendRedirect("cart.jsp");
                    return;
                }

                // Fetch product price from database
                Product product = productDAO.getProductById(productId);
                if (product == null) {
                    session.setAttribute("error", "Product not found.");
                    response.sendRedirect("cart.jsp");
                    return;
                }

                double price = product.getPrice();
                CartItem cartItem = new CartItem(user.getId(), productId, quantity, price);
                cartDAO.addToCart(cartItem);
            } 
            else if ("remove".equals(action)) {
                int cartItemId = Integer.parseInt(request.getParameter("cartItemId"));
                cartDAO.removeCartItem(cartItemId);
                //response.sendRedirect("cart.jsp");

            } 
            else if ("clear".equals(action)) {
                cartDAO.clearCart(user.getId());
            }

            response.sendRedirect("cart.jsp");
        } catch (NumberFormatException e) {
            session.setAttribute("error", "Invalid product or quantity.");
            response.sendRedirect("cart.jsp");
        } catch (SQLException e) {
            e.printStackTrace();
            session.setAttribute("error", "An error occurred while updating the cart.");
            response.sendRedirect("cart.jsp");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        if (user == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        try {
            List<CartItem> cartItems = cartDAO.getCartItems(user.getId());
            session.setAttribute("cartItems", cartItems);
            request.getRequestDispatcher("cart.jsp").forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
            session.setAttribute("error", "Unable to load cart.");
            response.sendRedirect("cart.jsp");
        }
    }
}
