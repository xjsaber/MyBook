<%--
  Created by IntelliJ IDEA.
  User: xjsaber
  Date: 3/6/2016
  Time: 11:14 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
//  request.setCharacterEncoding("UTF-8");
//  String name = request.getParameter("name");
//  String pwd = request.getParameter("pwd");
%>
<html>
  <head>
    <title>实例113防止表单在网站外部提交</title>
  </head>
  <body>
    <form name="form1" action="deal.jsp" ，method="post">
      用户名：<input name="name" type="text" id="name" style="width: 120px"/><br/>
      密码:<input name="pwd" type="password" id="pwd" style="width: 120px"/><br/>
      <br/>
      <input type="submit" name="Submit" value="提交"/>
    </form>
  </body>
</html>
