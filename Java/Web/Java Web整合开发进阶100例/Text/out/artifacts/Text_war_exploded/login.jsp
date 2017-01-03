<!--Struts2标签库调用声明-->
<%@ taglib prefix="s" uri="/struts-tags" %>
<%--
  Created by IntelliJ IDEA.
  User: xjsaber
  Date: 3/10/2016
  Time: 2:50 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登录页面</title>
</head>
<body>
    <!--form标签库定义，以及调用哪个Action声明-->
    <s:form action="Login">
        <table style=" width:60%; height: 76px; border: 0px">
            <!--各标签定义-->
            <s:textfield name="username" label="用户名"></s:textfield>
            <s:password name="password" label="密码"></s:password>
            <s:submit value="登录" align="center"></s:submit>
        </table>
    </s:form>
</body>
</html>
