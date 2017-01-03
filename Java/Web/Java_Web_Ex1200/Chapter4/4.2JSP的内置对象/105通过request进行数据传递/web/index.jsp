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
      try{                                            //捕获异常信息
        int money = 100;
        int number = 0;
        request.setAttribute("result", money/number); //保存执行结果
      }catch (Exception e){
        request.setAttribute("result", "很抱歉，页面产生错误"); //保存错误提示信息
      }
    %>
  <jsp:forward page="/deal.jsp"></jsp:forward>
  </body>
</html>
