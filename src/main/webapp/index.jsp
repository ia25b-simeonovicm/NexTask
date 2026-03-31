<%--
  Created by IntelliJ IDEA.
  User: jamie
  Date: 31.03.2026
  Time: 14:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login/Register</title>
    <link rel="stylesheet" href="css/login.css">
</head>
<body>
<div class="loginBackground">
    <div class="signIn">
        <div class="signInText">
            Sign In
        </div><br>
        <form class="signInInputs" action="/login" method="post">
            <input type="text" placeholder="E-Mail/Username" name="username"/><br><br>
            <input type="password" placeholder="Password" name="password"/><br><br>
            <input id="signInSubmit" type="submit" value="Sign In"/>
        </form>
    </div>
    <div class="signUp">
        <div class="signUpText">
            How could you still not be a User?
        </div><br><br>
        <input type="button" value="SignUp" onclick="signUp()">
    </div>
</div>

</body>
</html>
