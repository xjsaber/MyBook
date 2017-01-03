<%@ page import="java.util.Date" %>
<%--
  Created by IntelliJ IDEA.
  User: xjsaber
  Date: 3/6/2016
  Time: 11:14 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="mycounttime" class="com.count.stoptime.StopTime" scope="page"></jsp:useBean>
<%
  session.setMaxInactiveInterval(11); //设置session的有效活动时间为11s
  Date now = new Date();  //获取当前时间
  if (session.isNew()){
    session.setAttribute("startTime", now);
  }
  else{ //调用以用户登录时间为参数的counttime()方法，计算停留时间
    mycounttime.CountTime((Date)session.getAttribute(("startTime")));
  }

%>
<html>
  <head>
    <title>实例112用户在某一页停留的时间</title>
    <meta http-equiv="refresh" content="10"> <!--设置刷新页面-->
  </head>
  <body>
    <table style=" align-content: center">
      <tr style="background-color: #d3d3d3">
        <td style="align-content: center">您登录的时间为：<%=((Date)session.getAttribute("startTime")).toString()%></td>
      </tr>
      <tr>
        <td style="align-content: center">
          您在本页的停留时间为：<%=mycounttime.getH()%>小时<%=mycounttime.getM()%>分<%=mycounttime.getS()%>秒！
        </td>
      </tr>
    </table>
  </body>
</html>
