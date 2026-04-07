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
</head>
<body>
<header class="navbar">
    <nav>
        <a href="${pageContext.request.contextPath}/sites/task.jsp" class="nav-item">← Back</a>
        <a href="${pageContext.request.contextPath}/sites/overview.jsp" class="nav-item">Overview</a>
        <a href="${pageContext.request.contextPath}/sites/task.jsp" class="nav-item active">Tasks</a>
        <a href="${pageContext.request.contextPath}/category" class="nav-item active">Categories</a>
        <span class="nav-item">${sessionScope.user.username}</span>
    </nav>
</header>
<main>

</main>
</body>
</html>
