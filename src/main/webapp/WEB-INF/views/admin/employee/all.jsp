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
    <link rel="stylesheet" type="text/css"
          href="${contextPath}/resources/fonts/font-awesome-4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/normalize/5.0.0/normalize.min.css">
</head>

<body>
<jsp:include page="${contextPath}/WEB-INF/views/navbar/adminnavmenu.jsp"/>

<div class="container text-center">
    <h1>Employees List</h1>
    <input id="modal" type="checkbox">
    <label class="button" for="modal">
        <span>New Employee</span>
    </label>
    <label class="modal-background" for="modal"></label>
    <div class="modal-content">
        <main>
            <form action='/admin/employee/save' method="post" class='form'>
                <p class='field required half'>
                    <label class='label required' for='name'>NAME</label>
                    <input class='text-input' id='name' name='name' required type='text'>
                </p>
                <p class='field required half'>
                    <label class='label' for='surname'>SURNAME</label>
                    <input class='text-input' id='surname' name='surname'>
                </p>
                <p class='field required half'>
                    <label class='label' for='job'>JOB</label>
                    <select class="text-input" id='job' name="job" title="job">
                        <c:forEach items="${jobList}" var="job">
                            <option>${job.name}</option>
                        </c:forEach>
                    </select>
                </p>
                <p class='field required half'>
                    <label class='label' for='salary'>SALARY</label>
                    <input class='text-input' type="number" id='salary' name='salary'>
                </p>
                <p class='field required half'>
                    <label class='label' for='department'>DEPARTMENT</label>
                    <select class="text-input" id='department' name="department" title="department">
                        <c:forEach items="${listDepartment}" var="department">
                            <option value="${department.name}">${department.name} ${department.loc}</option>
                        </c:forEach>
                    </select>
                </p>
                <p class='field'>
                    <input class="button" type='submit' value='Save'>
                    <button class="button" type="reset">Reset</button>
                </p>
            </form>
        </main>
    </div> <!-- .modal-content -->
</div>

<div class="limiter">
    <div class="container-table100">
        <div class="wrap-table100">
            <div class="table100 ver1 m-b-110">
                <table data-vertable="ver1">
                    <thead>
                    <tr class="row100 head">
                        <th class="column100 column1" data-column="column1">ID</th>
                        <th class="column100 column2" data-column="column2">Name</th>
                        <th class="column100 column3" data-column="column3">Surname</th>
                        <th class="column100 column4" data-column="column4">Job</th>
                        <th class="column100 column5" data-column="column5">Hiredate</th>
                        <th class="column100 column6" data-column="column6">Salary</th>
                        <th class="column100 column7" data-column="column7">Department Name</th>
                        <th class="column100 column8" data-column="column8">Action</th>
                        <c:forEach items="${listEmployee}" varStatus="status" var="employee">
                    <tr class="row100">
                        <td class="column100 column1" data-column="column1">${employee.id}</td>
                        <td class="column100 column2" data-column="column2">${employee.name}</td>
                        <td class="column100 column3" data-column="column3">${employee.surname}</td>
                        <td class="column100 column4" data-column="column4">${employee.job}</td>
                        <td class="column100 column5" data-column="column5">${employee.hiredate}</td>
                        <td class="column100 column6" data-column="column6">${employee.salary} USD</td>
                        <td class="column100 column7" data-column="column7">${employee.departmentName}</td>
                        <td>
                            <a class="column100 atable" data-column="column4"
                               href="<c:url value="/admin/employee/edit/${employee.id}"/>">Edit</a>
                            &nbsp;&nbsp;&nbsp;&nbsp;
                            <a class="column100 atable" data-column="column4"
                               href="<c:url value="/admin/employee/delete/${employee.id}"/>">Delete</a>
                        </td>
                    </tr>
                    </c:forEach>
                    </thead>
                </table>
            </div>
        </div>
    </div>
</div>
<script src='https://cdnjs.cloudflare.com/ajax/libs/jquery/3.1.0/jquery.min.js'></script>
<script src="${contextPath}/resources/js/bootstrap.min.js"></script>
<script src="${contextPath}/resources/js/index.js"></script>
<script src="${contextPath}/resources/js/modal.js"></script>
</body>
</html>