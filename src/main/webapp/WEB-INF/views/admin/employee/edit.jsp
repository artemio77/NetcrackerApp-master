<%--
  Created by IntelliJ IDEA.
  User: Artem
  Date: 19.01.2018
  Time: 15:25
  To change this template use File | Settings | File Templates.
--%>
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
<jsp:include page="${contextPath}/WEB-INF/views/navbar/adminnavmenu.jsp"/>

<div class="editform">
    <main>
        <form action='/admin/employee/update' method="post" class='form'>
            <p class='field required half'>
                <input type="hidden" name="id" value="${employee.id}">
                <label class='label required' for='name'>NAME</label>
                <input class='text-input' id='name' name='name' required type='text'
                       value="${employee.name}">
            </p>
            <p class='field required half'>
                <label class='label' for='surname'>SURNAME</label>
                <input class='text-input' id='surname' name='surname' value="${employee.surname}">
            </p>
            <p class='field required half'>
                <label class='label' for='job'>JOB</label>
                <select class="text-input" id='job' name="job" title="job">
                    <c:forEach items="${listJob}" var="job">
                        <option>${job.name}</option>
                    </c:forEach>
                </select>
            </p>
            <p class='field required half'>
                <label class='label' for='salary'>SALARY</label>
                <input class='text-input' type="number" id='salary' name='salary'
                       value="${employee.salary}">
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
</div>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script src="${contextPath}/resources/js/bootstrap.min.js"></script>
<script src="${contextPath}/resources/js/index.js"></script>
<script>
    (function () {
        var floatingLabel;

        floatingLabel = (function () {
            function floatingLabel(form, options) {
                var event, i, input, j, label, len, len1, ref, ref1;
                if (options == null) {
                    options = {};
                }
                if (!form) {
                    return;
                }
                options.focusClass || (options.focusClass = "focus");
                options.activeClass || (options.activeClass = "active");
                options.errorClass || (options.errorClass = "error");
                form.classList.add('has-floated-label');
                ref = form.querySelectorAll('label');
                for (i = 0, len = ref.length; i < len; i++) {
                    label = ref[i];
                    if (!(input = document.querySelector("#" + (label.getAttribute('for'))))) {
                        return;
                    }
                    ref1 = ['keyup', 'input', 'change'];
                    for (j = 0, len1 = ref1.length; j < len1; j++) {
                        event = ref1[j];
                        input.addEventListener(event, function () {
                            this.parentNode.classList.remove(options.errorClass);
                            return this.parentNode.classList.toggle(options.activeClass, !!this.value);
                        });
                    }
                    input.addEventListener('focus', function () {
                        return this.parentNode.classList.add(options.focusClass);
                    });
                    input.addEventListener('blur', function () {
                        return this.parentNode.classList.remove(options.focusClass);
                    });
                    input.parentNode.classList.toggle(options.activeClass, !!input.value);
                }
            }

            return floatingLabel;

        })();

        window.floatingLabel = new floatingLabel(document.querySelector('.form'));

    }).call(this);
</script>
</body>
</html>
