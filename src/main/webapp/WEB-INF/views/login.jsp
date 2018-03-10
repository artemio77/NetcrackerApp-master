<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="input" uri="http://www.springframework.org/tags/form" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Log in with your account</title>
    <link href="${contextPath}/resources/css/stylelogin.css" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Montserrat:300, 400, 500" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/meyer-reset/2.0/reset.min.css">
    <script src="//ajax.googleapis.com/ajax/libs/angularjs/x.x.x/angular.min.js"></script>
    <script src="${contextPath}/resources/js/angulars.ts"></script>
</head>

<body>

<section class="user">
    <div class="user_options-container">
        <div class="user_options-text">
            <div class="user_options-unregistered">
                <h2 class="user_unregistered-title">Don't have an account?</h2>
                <button class="user_unregistered-signup" id="signup-button">Sign up</button>
            </div>

            <div class="user_options-registered">
                <h2 class="user_registered-title">Have an account?</h2>
                <button class="user_registered-login" id="login-button">Login</button>
            </div>
        </div>

        <div class="user_options-forms" id="user_options-forms">
            <div class="user_forms-login">
                <h2 class="forms_title">Login</h2><span id="message">${message}</span>
                <form method="POST" action="login" class="forms_form">
                    <fieldset class="forms_fieldset">
                        <div class="forms_field">
                            <input name="username" type="text" class="forms_field-input" placeholder="Username"
                                   autofocus="true"/>
                        </div>
                        <div class="forms_field">
                            <input name="password" type="password" class="forms_field-input" placeholder="Password"/>
                        </div>
                    </fieldset>
                    <div class="forms_buttons">
                        <span class="user_unregistered-title">${error}</span>
                        <input type="submit" value="Log In" class="forms_buttons-action">
                    </div>
                </form>
            </div>
            <div class="user_forms-signup">
                <h2 class="forms_title">Sign Up</h2>
                <form:form method="POST" modelAttribute="registrationForm" action="/login/registrForm"
                           class="forms_form">
                    <fieldset class="forms_fieldset">
                        <div class="forms_field">
                            <spring:bind path="username">
                                <span>${errorUsername}</span>
                                <form:input path="username" name="username" type="text" class="forms_field-input"
                                            placeholder="Username"
                                            autofocus="true"/>
                                <form:errors path="username"></form:errors>
                            </spring:bind>
                        </div>
                        <div class="forms_field">
                            <spring:bind path="password">
                                <span>${errorUsername}</span>
                                <form:input name="password" path="password" type="password" class="forms_field-input"
                                            placeholder="Password"/>
                                <form:errors path="password"></form:errors>
                            </spring:bind>
                        </div>
                        <div class="forms_field">
                            <spring:bind path="role">
                                <form:select path="role" cssClass="cs-select cs-skin-border forms_field-input"
                                             title="Role">
                                    <option value="" disabled selected>Role</option>
                                    <option value="2">ADMIN</option>
                                    <option value="3">ACCOUNTANT</option>
                                    <option value="1">USER</option>
                                </form:select>
                            </spring:bind>
                        </div>
                        <div class="forms_field">
                            <span>${error}</span>
                            <spring:bind path="confirmPassword">
                                <form:input name="confirmPassword" path="confirmPassword" type="password"
                                            class="forms_field-input" placeholder="Confirm Password"/>
                                <form:errors path="confirmPassword"></form:errors>
                            </spring:bind>
                        </div>
                    </fieldset>
                    <div class="forms_buttons">
                        <input type="submit" value="Log In" class="forms_buttons-action">
                    </div>
                </form:form>
            </div>
        </div>
    </div>
</section>
<!-- /container -->
<script src="../../resources/js/loginjs.js"></script>
<script>
    (function () {
        [].slice.call(document.querySelectorAll('select.cs-select')).forEach(function (el) {
            new SelectFx(el);
        });
    })();
</script>
</body>
</html>