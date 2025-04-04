<%@ page session="true" %>
<%@ page import="com.ecommerce.dao.OrderDao, com.ecommerce.model.User" %>
<%@ page import="com.ecommerce.util.DBConnection" %>
<%@ page import="java.sql.Connection" %>
<%
    User user = (User) session.getAttribute("user");
    if (user == null) {
        response.sendRedirect("login.jsp");
        return;
    }
%>
<html>
<head>
    <title>Checkout</title>
</head>
<body>
    <h2>Checkout</h2>
    <form action="order" method="post">
        <button type="submit">Place Order</button>
    </form>
</body>
</html>
