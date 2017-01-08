<%--
  Created by IntelliJ IDEA.
  User: xjsaber
  Date: 2017/1/8
  Time: 14:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" session="false"%>
<html>
<head>
    <title>Customer Support</title>
</head>
<body>
    <h2>Create a Ticket</h2>
    <form method="post" action="tickets" enctype="multipart/form-data">
        <input type="hidden" name="action" value="create" />
        Your Name<br/>
        <input type="text" name="subject" /><br/><br/>
        Body<br/>
        <textarea name="body" rows="5" cols="30"></textarea><br/><br/>
        <b>Attachments</b><br/>
        <input type="file" name="file1" /><br/><br/>
        <input type="submit" value="Submit" />
    </form>
</body>
</html>
