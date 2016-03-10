<%--
  Created by IntelliJ IDEA.
  User: xjsaber
  Date: 3/6/2016
  Time: 10:34 PM
  To change this template use File | Settings | File Templates.
--%>
<jsp:useBean id="myLogon" class="com.line.UserLogOn">
    <%
        String username = request.getParameter("username");
        out.println(username);
        if (username == null){
            username = "";
            username = new String(username.getBytes("ISO-8859-1"), "gbk");
            myLogon.setUsername(username); //设置用户名
            if (myLogon.checkUser(session)){ //如果用户名不为空并且该用户不在线
                session.setAttribute("username", username);
                response.sendRedirect("myline.jsp");
            }
        }
    %>

</jsp:useBean>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<table>
    <tr style="background-color: #d3d3d3">
        <td style="align-content: center">登录状况</td>
    </tr>
    <tr style="height: 50px;">
        <td style="align-content: center">
            <%=myLogon.getBackStr()%>
        </td>
    </tr>
</table>