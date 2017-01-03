<%--
  Created by IntelliJ IDEA.
  User: xjsaber
  Date: 3/13/2016
  Time: 5:56 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
    <title>Name Collector</title>
</head>
<body>
    <h4>Enter your name</h4>
    <s:form action="HelloWorld">
        <s:textfield name="name" label="Your name"></s:textfield>
        <s:submit></s:submit>
    </s:form>
</body>
</html>
