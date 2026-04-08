<%@ page isELIgnored="false" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%-- Verweist auf Login page falls nicht eingeloggt --%>
<c:if test="${empty sessionScope.user}">
    <c:redirect url="${pageContext.request.contextPath}/index.jsp"/>
</c:if>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Overview</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/tasks.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/overview.css">
</head>
<body>
<header class="navbar">
    <nav>
        <a href="${pageContext.request.contextPath}/sites/overview.jsp" class="nav-item active">Overview</a>
        <a href="${pageContext.request.contextPath}/sites/task.jsp" class="nav-item">Tasks</a>
        <a href="${pageContext.request.contextPath}/category" class="nav-item">Categories</a>
        <a href="${pageContext.request.contextPath}/sites/settings.jsp" class="nav-item">${sessionScope.user.username}</a>
    </nav>
</header>
<main class="container">
    <div class="construction">
        Under Construction🏗️
    </div>
</main>
</body>
</html>
