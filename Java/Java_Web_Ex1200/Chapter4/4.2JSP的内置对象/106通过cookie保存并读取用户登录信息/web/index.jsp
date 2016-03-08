<%@ page import="java.net.URLDecoder" %>
<%@ page import="java.net.URL" %>
<%@ page import="java.net.URI" %>
<%--
  Created by IntelliJ IDEA.
  User: xjsaber
  Date: 3/6/2016
  Time: 11:14 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
  request.setCharacterEncoding("UTF-8");
  String name = request.getParameter("name");
  String pwd = request.getParameter("pwd");
%>
<html>
  <head>
    <title>实例105通过request对象进行数据传递</title>
  </head>
  <body>
    <%
      Cookie[] cookies = request.getCookies(); //从request中获得Cookie对象的集合
      String user = ""; //登录用户
      String date = ""; //注册的时间
      if (cookies != null){
        for (int i = 0; i < cookies.length; i++){ //遍历cookie对象的集合
          if (cookies[i].getName().equals("mrCookie")){ //如果cookie对象的名称为mrCookie
            user = URLDecoder.decode(cookies[i].getValue().split("#")[0], "UTF-8"); //获取用户名
            date = cookies[i].getValue().split("#")[1]; //获取注册时间
          }
        }
      }
      if ("".equals(user) && "".equals(date)){
    %>
        游客您好，欢迎您初次光临！
        <form action="deal.jsp" method="post">
          请输入姓名：<input name="user" type="text" value="">
          <input type="submit" value="确定">
        </form>
    <%
      }else{
    %>
      欢迎[<b><%=user %></b>]再次光临<br>
    <%
      }
    %>
  </body>
</html>
