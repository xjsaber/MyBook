<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.sql.ResultSet" %><%--
  Created by IntelliJ IDEA.
  User: xjsaber
  Date: 3/6/2016
  Time: 11:14 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="mycount" class="com.line.CountOnLine"></jsp:useBean>
<jsp:useBean id="mydb" class="com.line.DB"></jsp:useBean>
<%
  SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
  String sql = "select MAX(user_order) from tb_IPcount as max"; //查询最后一次登录的次数
  ResultSet resultSet = mydb.search(sql);
  resultSet.next();
  int max = resultSet.getInt(1);
  mydb.closed();
  mycount.set
%>
<html>
  <head>
    <title>实例108防止表单在网站外部提交</title>
  </head>
  <body>
    <table style=" align-content: center">
      <tr style="background-color: #d3d3d3">
        <td style="align-content: center">第N位访问者</td>
        <td style="align-content: center">访问者IP地址</td>
        <td style="align-content: center">访问时间</td>
      </tr>
      <%
        while()
      %>
      <tr>
        <td style="align-content: center">
          您是第<b><%=i%></b>位访问本网站的游客！
        </td>
      </tr>
    </table>
  </body>
</html>
