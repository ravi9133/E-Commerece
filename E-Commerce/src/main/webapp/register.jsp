<html>
<head>
    <title>Register</title>
</head>
<body>
    <h2>Register</h2>
    <form action="register" method="post">
        <input type="text" name="username" placeholder="Username" required>
        <input type="email" name="email" placeholder="Email" required>
        <input type="password" name="password" placeholder="Password" required>

        <!-- Add a dropdown for role selection -->
        <label for="role">Role:</label>
        <select name="role">
            <option value="user">User</option>
            <option value="admin">Admin</option>
        </select>

        <button type="submit">Register</button>
    </form>
    <p>Already have an account? <a href="login.jsp">Login</a></p>
</body>
</html>
