<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Welcome</title>
    <link href="${contextPath}/resources/css/style.css" rel="stylesheet" type="text/css">
    <link href="${contextPath}/resources/css/bootstrap.min.css" rel="stylesheet" type="text/css">
</head>
<body>
<c:if test="${auth_user.role =='ROLE_ADMIN'}">
    <jsp:include page="${contextPath}/WEB-INF/views/navbar/adminnavmenu.jsp"/>
</c:if>
<c:if test="${auth_user.role =='ROLE_USER'}">
    <jsp:include page="${contextPath}/WEB-INF/views/navbar/usernavmenu.jsp"/>
</c:if>
<c:if test="${auth_user.role =='ROLE_ACCOUNTANT'}">
    <jsp:include page="${contextPath}/WEB-INF/views/navbar/accountantnavbar.jsp"/>
</c:if>
<script src="${contextPath}/resources/my-appnpm/src/build.js"></script>
<script src="${contextPath}/../../../reactfront/src/index.jsx"></script>
</body>
</html>