<%--
  Created by IntelliJ IDEA.
  User: xjsaber
  Date: 3/5/2016
  Time: 6:42 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String[] bookName = {"JavaWeb典型模块打全1", "JavaWeb典型模块打全2"};
%>
<html>
  <head>
    <title>$Title$</title>
  </head>
  <body>
    <table>
      <tr>
          <td align="center">编号</td>
          <td align="center">书名</td>
      </tr>
        <%for(int i=0; i < bookName.length; i++) {%>
        <tr>
            <td align="center"><%=i %></td>
            <td align="center"><%=bookName[i] %></td>
        </tr>
        <% } %>
    </table>
  </body>
</html>
