<%@ page isELIgnored="false" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page import="nextask.dao.KategorieDAO" %>
<%@ page import="nextask.model.User" %>
<%@ page import="nextask.dao.ToDoDAO" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%-- Verweist auf Login page falls nicht eingeloggt --%>
<c:if test="${empty sessionScope.user}">
    <c:redirect url="${pageContext.request.contextPath}/index.jsp"/>
</c:if>
<%
    User user = (User) session.getAttribute("user");
    KategorieDAO katdao = new KategorieDAO();
    ToDoDAO tododao = new ToDoDAO();
    request.setAttribute("categories", katdao.getAllKategorieByUser(user.getUserID()));
    request.setAttribute("todos", tododao.getAllToDoByUser(user.getUserID()));
%>

<!DOCTYPE html>
<html lang="de">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>To-Do Template</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/tasks.css">
</head>
<style>
    <c:forEach var="category" items="${categories}">
    .category-${fn:replace(category.name, ' ', '-')} {
        background: ${category.color};
    }

    </c:forEach>
</style>
<body>
<header class="navbar">
    <nav>
        <a href="${pageContext.request.contextPath}/sites/overview.jsp" class="nav-item">Overview</a>
        <a href="${pageContext.request.contextPath}/sites/task.jsp" class="nav-item active">Tasks</a>
        <a href="${pageContext.request.contextPath}/category" class="nav-item">Categories</a>
        <a href="${pageContext.request.contextPath}/sites/settings.jsp"
           class="nav-item">${sessionScope.user.username}</a>
    </nav>
</header>

<main class="grid-container" id="todo-container">
    <c:choose>
        <c:when test="${not empty todos}">
            <c:forEach var="todo" items="${todos}">
                <a href="${pageContext.request.contextPath}/todoUpdate?id=${todo.toDoID}" class="todo-card-link">
                    <div class="todo-card category-${fn:replace(todo.kategorie.name, ' ', '-')}">
                        <div class="todo-content">
                            <div class="todo-category-badge">${todo.kategorie.name}</div>
                            <div class="todo-title">${todo.title}</div>
                            <div class="todo-desc">${todo.description}</div>
                        </div>
                    </div>
                </a>
            </c:forEach>
        </c:when>
        <c:otherwise>
            <div>Keine Todos erstellt...</div>
        </c:otherwise>
    </c:choose>

    <a class="addBtn" href="${pageContext.request.contextPath}/sites/addTodo.jsp">+</a>
</main>
</body>
</html>