<%@ page import="com.xjsaber.Ticket" %>
<%@ page import="com.xjsaber.Attachment" %><%--
  Created by IntelliJ IDEA.
  User: xjsaber
  Date: 2017/1/8
  Time: 14:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String ticketId = (String)request.getAttribute("ticketId");
    Ticket ticket = (Ticket)request.getAttribute("ticket");
%>
<html>
<head>
    <title>Customer Support</title>
</head>
<body>
    <h2>Ticket #<%= ticketId %>: <%= ticket.getSubject() %></h2>
    <i>Customer Name</i>
    <%= ticket.getBody() %><br/><br/>
    <%
    if (ticket.getNumberOfAttachments() > 0)
    {
        %>Attachments: <%
        int i = 0;
        for (Attachment a : ticket.getAttachments())
        {
            if (i++ > 0) {
                System.out.print(", ");
            }
            %><a href="<c:url value="/tickets">
                <c:param name="action" value="download" />
                <c:param name="ticketId" value="download" />"
                <c:param name="attachment" value="<% a.getName() %>" />
            </c:url>"><%= a.getName() %></a><%
        }
    }
    %>
    <a href="<c:url value="/tickets" />">Return to list tickets</a>
</body>
</html>
