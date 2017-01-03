<%--
  Created by IntelliJ IDEA.
  User: xjsaber
  Date: 3/8/2016
  Time: 8:51 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>结果页</title>
</head>
<body>
<%String message=request.getAttribute("result").toString();%>
<%=message%>
</body>
</html>
