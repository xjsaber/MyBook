<%--
  Created by IntelliJ IDEA.
  User: xjsaber
  Date: 2017/1/8
  Time: 15:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" import="java.util.Map" %>
<%@ page import="com.xjsaber.Ticket" %>
<%
    @SuppressWarnings("unchecked")
    Map<Integer, Ticket> ticketDatabase = (Map<Integer, Ticket>)request.getAttribute("ticketDatabase");
%>
<html>
<head>
    <title>Customer Support</title>
</head>
<body>
    <h2>Tickets</h2>
        <a href="<c:url value="/tickets">
        <c:param name="action" value="create" /></c:url>">Create Ticket</a><br/><br/>
    <%
        if (ticketDatabase.size() == 0)
        {
    %>
        <i>There are no tickets in the system.</i>
    <%
        }
        else
        {
            for (int id : ticketDatabase.keySet())
            {
                String idString = Integer.toString(id);
                Ticket ticket = ticketDatabase.get(id);
    %>
                Ticket #<%= idString %>: <a href="<c:url value="/tickets">
                    <c:param name="action" value="view" />
                    <c:param name="tickedId" value="<%= idString %>" />
                </c:url>"><% ticket.getSubject(); %></a> (customer: <%= ticket.getCustomerName() %>)<br />
    <%
            }
        }
    %>
</body>
</html>
