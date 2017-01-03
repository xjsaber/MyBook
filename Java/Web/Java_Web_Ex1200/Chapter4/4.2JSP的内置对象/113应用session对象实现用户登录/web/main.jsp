<%--
  Created by IntelliJ IDEA.
  User: xjsaber
  Date: 3/9/2016
  Time: 9:50 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
String username = (String)session.getAttribute("username"); //获取保存在session范围内的用户名
%>
<html>
<head>
    <meta http-equiv="Context_Type" content="text/html;charset=GB18030"/>
    <title>系统主页</title>
</head>
<body>
您好！[<%=username%>]欢迎你访问！<br/>
<a href="exit.jsp">[退出]</a>
</body>
</html>
