<%--
  Created by IntelliJ IDEA.
  User: Artem
  Date: 22.02.2018
  Time: 13:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link href="${contextPath}/resources/css/style.css" rel="stylesheet">
    <link href="${contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" type="text/css"
          href="${contextPath}/resources/fonts/font-awesome-4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" type="text/css" href="${contextPath}/resources/vendor/animate/animate.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="${contextPath}/resources/vendor/select2/select2.min.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css"
          href="${contextPath}/resources/vendor/perfect-scrollbar/perfect-scrollbar.css">
</head>
<body>
<ul class="menu">
    <li><a href="/" class="amain">Home</a></li>
    <li><a href="/" class="amain">Department List</a></li>
    <li><a href="/accountant/employee/" class="amain">Employees List</a></li>
    <li><a class="amain" href="/">${pageContext.request.userPrincipal.name} | ${auth_user.role}</a></li>
    <li><a class="amain" href="/logout">| Logout</a></li>
    <li class="slider"></li>
</ul>
</body>
</html>
