<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    HttpSession httpsession = request.getSession(false);
    if (session != null) {
        session.invalidate();
    }
    response.sendRedirect("index.jsp");
%>