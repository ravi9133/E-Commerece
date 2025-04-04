<%@ page import="com.ecommerce.dao.ProductDao, com.ecommerce.model.Product, java.util.List" %>
<%@ page import="com.ecommerce.util.DBConnection" %>
<%@ page import="java.sql.Connection" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%
    Connection conn = DBConnection.getConnection();
    ProductDao productDao = new ProductDao(conn);
    List<Product> products = productDao.getAllProducts();
%>
<html>
<head>
    <title>Home - E-Commerce</title>
    <link rel="stylesheet" href="styles.css">
</head>
<body>
    <h2>Welcome to Our Store</h2>
    <a href="cart.jsp">View Cart</a> | <a href="login.jsp">Login</a> | <a href="register.jsp">Register</a>
    <div class="product-list">
        <% for (Product p : products) { %>
        <div class="product">
            <h3><%= p.getName() %></h3>
            <p><%= p.getDescription() %></p>
            <p>Price: $<%= p.getPrice() %></p>
            <form action="cart" method="post">
                <input type="hidden" name="productId" value="<%= p.getId() %>">
                <input type="number" name="quantity" value="1" min="1">
                <button type="submit">Add to Cart</button>
            </form>
        </div>
        <% } %>
    </div>
</body>
</html>
