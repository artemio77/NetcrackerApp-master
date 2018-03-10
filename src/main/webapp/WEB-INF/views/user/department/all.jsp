<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
    <title>Admin</title>
    <link href="${contextPath}/resources/css/style.css" rel="stylesheet">
    <link href="${contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" type="text/css"
          href="${contextPath}/resources/fonts/font-awesome-4.7.0/css/font-awesome.min.css">
</head>

<body>
<jsp:include page="${contextPath}/WEB-INF/views/navbar/usernavmenu.jsp"/>
<div class="container text-center">
    <h1>Departments List</h1>
    <div class="limiter">
        <div class="container-table100">
            <div class="wrap-table100">
                <div class="table100 ver1 m-b-110">
                    <table data-vertable="ver1">
                        <thead>
                        <tr class="row100 head">
                            <th class="column100 column1" data-column="column1">ID</th>
                            <th class="column100 column2" data-column="column2">Name</th>
                            <th class="column100 column3" data-column="column3">Location</th>
                            <c:forEach items="${departmentList}" varStatus="status" var="department">
                        <tr class="row100">
                            <td class="column100 column1" data-column="column1">${department.id}</td>
                            <td class="column100 column2" data-column="column2">${department.name}</td>
                            <td class="column100 column3" data-column="column3">${department.loc}</td>
                        </tr>
                        </c:forEach>
                        </tr>
                        </thead>
                    </table>
                </div>
            </div>
        </div>
    </div>
</div>
<script src="${contextPath}/resources/js/bootstrap.min.js"></script>
<script src="${contextPath}/resources/js/index.js"></script>


</body>
</html>