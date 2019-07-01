<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>主页</title>
</head>
<body>
    <%
        response.sendRedirect(request.getContextPath() + "/Index");
    %>
</body>
</html>
