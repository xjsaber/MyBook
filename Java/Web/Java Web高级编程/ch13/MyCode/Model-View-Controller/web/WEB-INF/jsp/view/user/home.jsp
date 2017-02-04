<%--
  Created by IntelliJ IDEA.
  User: xjsaber
  Date: 2017/2/2
  Time: 17:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--@elvariable id="currentUser" type="xjsaber.spring.javastart.site.User"--%>
<html>
<head>
    <title>User Home</title>
</head>
<body>
    ID: ${ currentUser.userId }<br />
    Username: ${ currentUser.username }<br />
    Name: ${ currentUser.name }<br />
</body>
</html>
