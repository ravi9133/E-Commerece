<%@ page import="com.ecommerce.dao.ProductDao, com.ecommerce.model.Product, java.util.List" %>
<%@ page import="com.ecommerce.util.DBConnection" %>
<%@ page import="java.sql.Connection" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page session="true" %>

<%
    Connection conn = DBConnection.getConnection();
    ProductDao productDao = new ProductDao(conn);
    List<Product> products = productDao.getAllProducts();
%>

<html>
<head>
    <title>Admin Panel</title>
    <link rel="stylesheet" href="styles.css">
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script>
        function updateProduct(productId) {
            let name = $("#name-" + productId).val();
            let description = $("#desc-" + productId).val();
            let price = $("#price-" + productId).val();
            let stock = $("#stock-" + productId).val();
            let imageUrl = $("#image-" + productId).val();

            $.ajax({
                url: "ProductServlet",
                type: "POST",
                data: {
                    id: productId,
                    name: name,
                    description: description,
                    price: price,
                    stock: stock,
                    imageUrl: imageUrl,
                    action: "update"
                },
                success: function(response) {
                    alert("Product updated successfully!");
                    location.reload();
                },
                error: function() {
                    alert("Failed to update product.");
                }
            });
        }
    </script>
</head>
<body>
    <h2>Admin Dashboard</h2>
    
    <!-- Add Product Form -->
    <h3>Add New Product</h3>
    <form action="ProductServlet" method="post">
        <input type="text" name="name" placeholder="Product Name" required>
        <input type="text" name="description" placeholder="Description" required>
        <input type="number" name="price" placeholder="Price" required>
        <input type="number" name="stock" placeholder="Stock" required>
        <input type="text" name="imageUrl" placeholder="Image URL">
        <input type="hidden" name="action" value="add">
        <button type="submit">Add Product</button>
    </form>

    <h3>Products List</h3>
    <table border="1">
        <tr>
            <th>Name</th><th>Description</th><th>Price</th><th>Stock</th><th>Image</th><th>Action</th>
        </tr>
        <% for (Product p : products) { %>
        <tr>
            <td><input type="text" id="name-<%= p.getId() %>" value="<%= p.getName() %>"></td>
            <td><input type="text" id="desc-<%= p.getId() %>" value="<%= p.getDescription() %>"></td>
            <td><input type="number" id="price-<%= p.getId() %>" value="<%= p.getPrice() %>"></td>
            <td><input type="number" id="stock-<%= p.getId() %>" value="<%= p.getStock() %>"></td>
            <td><input type="text" id="image-<%= p.getId() %>" value="<%= p.getImageUrl() %>"></td>
            <td>
                <button onclick="updateProduct(<%= p.getId() %>)">Update</button>
                <form action="ProductServlet" method="post" style="display:inline;">
                    <input type="hidden" name="id" value="<%= p.getId() %>">
                    <input type="hidden" name="action" value="delete">
                    <button type="submit">Delete</button>
                </form>
            </td>
        </tr>
        <% } %>
    </table>
</body>
</html>
