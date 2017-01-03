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
    <title>实力104将表单请求提交到本页</title>
  </head>
  <body>
    <form action="index.jsp" ，method="post">
      <table style=" height: 150px; width: 300px;">
        <tr>
          <td style=" align:center; column-span: 4; height: 20px;">用户登录</td>
        </tr>
        <tr>
          <td>
            用户名:
          </td>
          <td>
            <input type="text" name="name" />
          </td>
        </tr>
        <tr>
          <td>
            密码:
          </td>
          <td>
            <input type="password" name="pwd" />
          </td>
        </tr>
        <tr>
          <td style="column-span: 2">
            <input type="submit" name="login" value="登录"/>
          </td>
        </tr>
        <tr>
          <td><label>用户名参数为：</label></td>
          <td><%=name%></td>
        </tr>
        <tr>
          <td><label>密码参数为：</label></td>
          <td><%=pwd%></td>
        </tr>
        <tr><td style="align-content: center; column-span: 4; height: 20px;" ></td></tr>
      </table>
    </form>
  </body>
</html>
