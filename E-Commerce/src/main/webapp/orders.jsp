<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.ecommerce.model.Order" %>
<%@ page import="com.ecommerce.model.OrderItem" %>
<%
    List<Order> orderList = (List<Order>) request.getAttribute("orderList");
%>
<html>
<head>
    <title>Admin - Manage Orders</title>
    <link rel="stylesheet" type="text/css" href="../css/styles.css">
</head>
<body>
    <h1>Manage Orders</h1>
    <div class="order-list">
        <%
            if (orderList != null) {
                for (Order order : orderList) {
        %>
        <div class="order-item">
            <h3>Order ID: <%= order.getId() %></h3>
            <p>Order Date: <%= order.getOrderDate() %></p>
            <p>Status: <%= order.getStatus() %></p>
            <h4>Items:</h4>
            <ul>
                <%
                    for (OrderItem item : order.getOrderItems()) {
                %>
                <li>
                    Product ID: <%= item.getProductId() %> - Quantity: <%= item.getQuantity() %> - Price: $<%= item.getPrice() %>
                </li>
                <%
                    }
                %>
            </ul>
            <form action="orders" method="post">
                <input type="hidden" name="orderId" value="<%= order.getId() %>">
                <select name="status">
                    <option value="Pending" <%= order.getStatus().equals("Pending") ? "selected" : "" %>>Pending</option>
                    <option value="Shipped" <%= order.getStatus().equals("Shipped") ? "selected" : "" %>>Shipped</option>
                    <option value="Delivered" <%= order.getStatus().equals("Delivered") ? "selected" : "" %>>Delivered</option>
                </select>
                <button type="submit">Update Status</button>
            </form>
        </div>
        <%
                }
            }
        %>
    </div>
</body>
</html>