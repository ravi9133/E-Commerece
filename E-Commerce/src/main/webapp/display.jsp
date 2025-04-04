<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.ecommerce.model.Product" %>

<%
    List<Product> productList = (List<Product>) request.getAttribute("productList");
%>

<html>
<head>
    <title>Home</title>
    <link rel="stylesheet" type="text/css" href="css/styles.css">
</head>
<body>
    <h2>Products</h2>
    <div class="product-grid">
        <%
            if (productList != null && !productList.isEmpty()) {
                for (Product product : productList) {
        %>
        <div class="product-item">
            <h3><%= product.getName() %></h3>
            <img src="<%= product.getImageUrl() %>" alt="Product Image" width="100">
            <p><%= product.getDescription() %></p>
            <p>Price: $<%= product.getPrice() %></p>
            <form action="cart" method="post">
                <input type="hidden" name="productId" value="<%= product.getId() %>">
                <input type="number" name="quantity" min="1" value="1" required>
                <button type="submit">Add to Cart</button>
            </form>
        </div>
        <%
                }
            } else {
        %>
        <p>No products available.</p>
        <%
            }
        %>
    </div>
</body>
</html>
