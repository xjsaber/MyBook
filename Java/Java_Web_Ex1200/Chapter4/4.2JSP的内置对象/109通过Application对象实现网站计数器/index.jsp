<%--
  Created by IntelliJ IDEA.
  User: xjsaber
  Date: 3/6/2016
  Time: 11:14 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
  int i = 0;
  synchronized (application){
    if (application.getAttribute("times") == null){ //服务器启动后第一个访问者
      i = 1;
    }
    else{
      i = Integer.parseInt((String)application.getAttribute("times"));
      i++;  //访问次数加1
    }
    application.setAttribute("times", Integer.toString(i)); //将访问次数存入Application对象中
  }
%>
<html>
  <head>
    <title>实例108防止表单在网站外部提交</title>
  </head>
  <body>
    <form action="doform.jsp" ，method="post">
      <table style=" align-content: center">
        <tr style="background-color: #d3d3d3">
          <td style="align-content: center">欢迎访问！</td>
        </tr>
        <tr>
          <td style="align-content: center">
            您是第<b><%=i%></b>位访问本网站的游客！
          </td>
        </tr>
      </table>
    </form>
  </body>
</html>
