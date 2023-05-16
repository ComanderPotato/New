<%@ page import="iotbay.model.UserAccount" %><%--
  Created by IntelliJ IDEA.
  User: Tom
  Date: 16/05/2023
  Time: 9:08 pm
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%
    UserAccount name = (UserAccount) session.getAttribute("user");
    System.out.println(name.getPassword());
%>
    <h1><%= name.getEmail() %></h1>
</body>
</html>
