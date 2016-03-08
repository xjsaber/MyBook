<%@ page import="java.net.URL" %><%--
  Created by IntelliJ IDEA.
  User: xjsaber
  Date: 3/8/2016
  Time: 1:29 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String address = request.getHeader("referer"); //获取页面的请求地址
    String pathAdd = "";    //定义空字符串
    if (address != null){ //判断当页面的请求地址为空时
        URL urlOne = new URL(address); //实例化url方法
        pathAdd = urlOne.getHost(); //获取请求页面的服务器主机
    }
    String address1 = request.getRequestURI().toString(); //获取当前页面
    address1 = address + address1;
    String pathAdd1 = "";
    if (address1 != null){
        URL urlTwo = new URL(address1);
        pathAdd1 = urlTwo.getHost();    //获取当前页面的服务器主机
    }
%>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <table style=" align-content: center;">
        <tr>
            <td></td>
        </tr>
        <tr>
            <td>
                <% if (!pathAdd.equals(pathAdd1)){
                    out.println("当前页面的主机与服务器的主机是否相同");
                }%>
            </td>
        </tr>
    </table>
</body>
</html>
