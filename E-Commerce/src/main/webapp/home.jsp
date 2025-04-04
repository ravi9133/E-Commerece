<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ page import="java.util.List"%>
<%@ page
	import="com.ecommerce.model.User, com.ecommerce.model.Product, com.ecommerce.model.Order, com.ecommerce.model.OrderItem"%>
<%@ page import="com.ecommerce.dao.ProductDao"%>
<%@ page import="com.ecommerce.util.DBConnection"%>
<%@ page import="java.sql.Connection"%>

<%
// Get user from session
HttpSession httpSession = request.getSession(false);
User user = (httpSession != null) ? (User) httpSession.getAttribute("user") : null;

if (user == null) {
	response.sendRedirect("login.jsp");
	return;
}

// Fetch products
Connection conn = DBConnection.getConnection();
ProductDao productDao = new ProductDao(conn);
List<Product> products = productDao.getAllProducts();

// Fetch orders
List<Order> orderList = (List<Order>) request.getAttribute("orderList");
%>

<html>
<head>
<title>Online Shopping - Home</title>
<link rel="stylesheet" type="text/css" href="css/styles.css">
</head>
<body>

	<nav class="navbar">
		<div class="logo">Online Shopping</div>
		<div class="nav-links">
			<a href="home.jsp">Home</a> <a href="cart.jsp">Cart</a>
			<%
			if (user.isAdmin()) {
			%>
			<a href="admin.jsp">Admin Panel</a>
			<%
			}
			%>
			<a href="logout.jsp">Logout</a>
		</div>
	</nav>

	<h1>
		Welcome,
		<%=user.getUsername()%>!
	</h1>
	<p>
		Role:
		<%=user.getRole()%></p>

	<div class="product-section">
		<h2>Products</h2>
		<div class="product-grid">
			<%
			for (Product p : products) {
			%>
			<div class="product">
				<img src="<%=p.getImageUrl()%>" alt="<%=p.getName()%>">
				
				<h3><%=p.getName()%></h3>
				<p><%=p.getDescription()%></p>
				<p>
					Price: $<%=p.getPrice()%></p>
				<form action="cart" method="post">
					<input type="hidden" name="productId" value="<%=p.getId()%>">
					<input type="number" name="quantity" min="1" value="1"> 
					<input type="hidden" name="action" value="add">
					<button type="submit">Add to Cart</button>
				</form>
			</div>
			<%
			}
			%>
		</div>
	</div>


	<div class="order-section">
		<h2>Your Orders</h2>
		<%
		if (orderList != null && !orderList.isEmpty()) {
		%>
		<div class="order-list">
			<%
			for (Order order : orderList) {
			%>
			<div class="order-card">
				<h3>
					Order ID:
					<%=order.getId()%></h3>
				<p>
					Order Date:
					<%=order.getOrderDate()%></p>
				<p>
					Status:
					<%=order.getStatus()%></p>
				<h4>Items:</h4>
				<ul>
					<%
					for (OrderItem item : order.getOrderItems()) {
					%>
					<li>Product ID: <%=item.getProductId()%> - Quantity: <%=item.getQuantity()%>
						- Price: $<%=item.getPrice()%></li>
					<%
					}
					%>
				</ul>
			</div>
			<%
			}
			%>
		</div>
		<%
		} else {
		%>
		<p>You have no orders yet.</p>
		<%
		}
		%>
	</div>

</body>
</html>
