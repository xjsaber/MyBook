<%--
  Created by IntelliJ IDEA.
  User: xjsaber
  Date: 3/7/2016
  Time: 9:48 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="java.util.*" %>
<%
    session.setMaxInactiveInterval(10);
    Vector vector = (Vector)application.getAttribute("online");
%>
    <meta http-equiv="refresh" content="12"/>
<table>
    <tr>
        <td width="60%" style="vertical-align: middle; align-content: center" >10秒没有发言</td>
    </tr>
    <tr>
        <td style="align-content: center; vertical-align: middle;">
            <%
                if (vector == null || vector.size() == 0){
                    out.println("没用用户");
                }
                else{
                    if (vector.contains(session.getAttribute("username"))) {
                        out.print("你已经上线！ 用户名；" + session.getAttribute("username"));
                    }else{
                        out.println("你没有上线");
                    }
                }
            %>
        </td>
    </tr>
    <tr style="background-color: #d3d3d3;">
        <td style=" align-content: center; height: 25px; column-span: 2" >
            <%
                if (session.getAttribute("username") == null){
                    out.println("您已经下线！");
                }
                else{
                    if (vector.contains(session.getAttribute("username"))){
                        out.println("<a href='myline.jsp'>[发言]</a>");
                    }else{
                        out.println("请先登录！");
                    }
                }
            %>
        </td>
    </tr>
</table>
<%
    if (!vector.contains(session.getAttribute("username"))){
        out.println("<a href='index.jsp'>[登录]</a>");
    }
%>

