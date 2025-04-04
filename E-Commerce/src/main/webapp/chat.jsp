<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Chat</title>
    <link rel="stylesheet" type="text/css" href="css/styles.css">
    <script src="js/chat.js"></script>
</head>
<body>
    <div class="chat-container">
        <div id="chat-box">
            <!-- Messages will be displayed here -->
        </div>
        <form id="chat-form" method="post">
            <input type="text" id="receiver" name="receiver" placeholder="Enter username" required>
            <input type="text" id="message" name="message" placeholder="Enter message" required>
            <button type="submit">Send</button>
        </form>
    </div>
</body>
</html>