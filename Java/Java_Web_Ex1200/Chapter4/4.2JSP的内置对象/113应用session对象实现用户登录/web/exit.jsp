<%--
  Created by IntelliJ IDEA.
  User: xjsaber
  Date: 3/9/2016
  Time: 9:53 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%
    session.invalidate();   //销毁session
    response.sendRedirect("index.jsp"); //重定向页面到index.jsp
%>
</body>
</html>
