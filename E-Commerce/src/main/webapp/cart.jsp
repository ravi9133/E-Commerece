<%@ page import="java.util.List" %>
<%@ page import="com.ecommerce.model.CartItem, com.ecommerce.model.User" %>
<%@ page import="com.ecommerce.dao.CartDao" %>
<%@ page import="com.ecommerce.util.DBConnection" %>
<%@ page import="java.sql.Connection" %>

<%
    User user = (User) session.getAttribute("user");
    if (user == null) {
        response.sendRedirect("login.jsp");
        return;
    }

    Connection conn = DBConnection.getConnection();
    CartDao cartDao = new CartDao(conn);
    
    List<CartItem> cartItems = cartDao.getCartItems(user.getId());
%>

<html>
<head>
    <title>Shopping Cart</title>
</head>
<body>
    <h2>Your Cart</h2>

    <% if (cartItems == null || cartItems.isEmpty()) { %>
        <p>Your cart is empty.</p>
    <% } else { %>
        <table border="1">
            <tr>
                <th>Product ID</th>
                <th>Quantity</th>
                <th>Price</th>
                <th>Action</th>
            </tr>
            <% for (CartItem item : cartItems) { %>
                <tr>
                    <td><%= item.getProductId() %></td>
                    <td><%= item.getQuantity() %></td>
                    <td>$<%= item.getPrice() %></td>
                    <td>
                        <form action="cart" method="post">
                            <input type="hidden" name="cartItemId" value="<%= item.getId() %>">  <!-- Now using dynamic cartItemId -->
                            <input type="hidden" name="action" value="remove">
                            <button type="submit">Remove</button>
                        </form>
                    </td>
                </tr>
            <% } %>
        </table>

        <form action="cart" method="post">
            <input type="hidden" name="action" value="clear">
            <button type="submit">Clear Cart</button>
        </form>

        <a href="checkout.jsp">Proceed to Checkout</a>
    <% } %>
</body>
</html>
