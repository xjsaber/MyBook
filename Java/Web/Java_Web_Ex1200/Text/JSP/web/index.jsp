<%--
  Created by IntelliJ IDEA.
  User: xjsaber
  Date: 3/5/2016
  Time: 6:42 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
      <meta http-equiv="content-type" content="text/html"/>
      <title>ex52.统一站内网页风格</title>
      <link ref="stylesheet" type="text/css" href="mycss.css"/>
  </head>
  <body>
    <form action="">
        <table align="center">
            <tr>
                <td>用户名：</td><td><input type="text" name="name" /></td>
            </tr>
            <tr>
                <td>密码：</td><td><input type="password" name="pwd" /></td>
            </tr>
            <tr>
                <td>确认密码：</td><td><input type="password" name="rePwd" /></td>
            </tr>
            <tr>
                <td>
                    <input type="radio" name="sex" value="m"/>男
                    <input type="radio" name="sex" value="f"/>女
                </td>
            </tr>
        </table>
    </form>
  </body>
</html>
