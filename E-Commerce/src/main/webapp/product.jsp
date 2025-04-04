<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.ecommerce.model.Product" %>
<%
    List<Product> productList = (List<Product>) request.getAttribute("productList");
%>
<html>
<head>
    <title>Admin - Manage Products</title>
    <link rel="stylesheet" type="text/css" href="../css/styles.css">
</head>
<body>
    <h1>Manage Products</h1>
    <form action="products" method="post">
        <input type="hidden" name="action" value="add">
        <div>
            <label for="name">Product Name:</label>
            <input type="text" id="name" name="name" required>
        </div>
        <div>
            <label for="description">Description:</label>
            <textarea id="description" name="description" required></textarea>
        </div>
        <div>
            <label for="price">Price:</label>
            <input type="text" id="price" name="price" required>
        </div>
        <div>
            <button type="submit">Add Product</button>
        </div>
    </form>
    <h2>Product List</h2>
    <div class="product-grid">
        <%
            if (productList != null) {
                for (Product product : productList) {
        %>
        <div class="product-item">
            <h3><%= product.getName() %></h3>
            <p><%= product.getDescription() %></p>
            <p>Price: $<%= product.getPrice() %></p>
            <form action="products" method="post">
                <input type="hidden" name="action" value="delete">
                <input type="hidden" name="productId" value="<%= product.getId() %>">
                <button type="submit">Delete</button>
            </form>
        </div>
        <%
                }
            }
        %>
    </div>
</body>
</html>