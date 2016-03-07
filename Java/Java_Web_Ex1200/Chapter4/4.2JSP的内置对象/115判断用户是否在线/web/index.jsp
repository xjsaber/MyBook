<%--
  Created by IntelliJ IDEA.
  User: xjsaber
  Date: 3/6/2016
  Time: 11:14 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>$Title$</title>
  </head>
  <body>
    <form action="dologon.jsp">
      <table>
        <tr style="background-color: #d3d3d3; height: 30px;">
          <td align="center">用户登录</td>
        </tr>
        <tr style="height: 30px;">
          <td>
            用户名:<input type="text" name="username" size="30">
          </td>
        </tr>
        <tr style="background-color: #d3d3d3; height: 30px;">
          <td align="center">
            <input type="submit" name="login" value="登录"/>
            <input type="reset" name="clear" value="重置"/>
          </td>
        </tr>
      </table>
    </form>
  </body>
</html>
