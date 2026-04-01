<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="de">
<head>
    <title>Login/Register</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/login.css">
</head>
<body>
<div class="loginBackground">
    <div class="signIn" id="signInHolder">
        <div class="signInText">Sign In</div><br>
        <form class="signInInputs" action="${pageContext.request.contextPath}/login" method="post">
            <input type="text" placeholder="E-Mail/Username" name="username"/><br><br>
            <input type="password" placeholder="Password" name="password"/><br><br>
            <input id="signInSubmit" type="submit" value="Sign In"/>
            <c:if test="${not empty error}">
                <div class="error-message">
                        ${error}
                </div>
            </c:if>
        </form>
    </div>
    <div class="signUp" id="signUpHolder">
        <div class="signUpText">
            How could you still not be a user?
        </div><br><br>
        <form class="signInInputs" action="${pageContext.request.contextPath}/register" method="post" id="signUpForm" style="display:none;">
            <input type="text" placeholder="Username" name="username"/><br><br>
            <input type="email" placeholder="E-Mail" name="email"/><br><br>
            <input type="password" placeholder="Password" name="password"/><br><br>
            <input id="signInSubmit" type="submit" value="Register"/>
        </form>
        <input type="button" id="switchBtn" value="Sign Up" onclick="switchForm()"/>
    </div>
</div>
<script src="${pageContext.request.contextPath}/javascript/loginAnimation.js"></script>
</body>
</html>
