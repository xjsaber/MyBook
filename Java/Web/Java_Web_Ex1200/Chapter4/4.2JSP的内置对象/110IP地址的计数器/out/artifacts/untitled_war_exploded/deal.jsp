<%--
  Created by IntelliJ IDEA.
  User: xjsaber
  Date: 3/8/2016
  Time: 8:51 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.net.URLEncoder"%>
<html>
<head>
    <title>写入cookie</title>
</head>
<body>
<%
    request.setCharacterEncoding("GB18030");    //设置请求的编译为GB18030
    String user = URLEncoder.encode(request.getParameter("user"), "GB18030"); //获取用户名
    Cookie cookie = new Cookie("mrCookie", user+"#"+new java.util.Date().toString()); //创建并实例化Cookie对象
    cookie.setMaxAge(60*60*24*30);
    response.addCookie(cookie);
%>
<script type="text/javascript">window.location.href="index.jsp"</script>
</body>
</html>
