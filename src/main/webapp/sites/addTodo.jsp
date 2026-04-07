<%@ page isELIgnored="false" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page import="org.example.nextask.model.User" %>
<%@ page import="org.example.nextask.dao.KategorieDAO" %>
<%@ page isELIgnored="false" %>

<%-- Verweist auf Login page falls nicht eingeloggt --%>
<c:if test="${empty sessionScope.user}">
    <c:redirect url="${pageContext.request.contextPath}/index.jsp"/>
</c:if>
<%
    User user = (User) session.getAttribute("user");
    KategorieDAO katdao = new KategorieDAO();
    request.setAttribute("categories", katdao.getAllKategorieByUser(user.getUserID()));
%>

<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Create To-Do</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/tasks.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/addTodo.css">
</head>
<body>
<header class="navbar">
    <nav>
        <a href="${pageContext.request.contextPath}/sites/task.jsp" class="nav-item">← Back</a>
        <a href="${pageContext.request.contextPath}/sites/overview.jsp" class="nav-item">Overview</a>
        <a href="${pageContext.request.contextPath}/sites/task.jsp" class="nav-item active">Tasks</a>
        <a href="${pageContext.request.contextPath}/sites/addCategory.jsp" class="nav-item">Categories</a>
        <span class="nav-item">👤 ${sessionScope.user.username}</span>
         </nav>
</header>
<main>
    <div class="form-container">
        <h1 class="form-title">New Task</h1>
        <form action="${pageContext.request.contextPath}/todo" method="post" class="todo-form">
            <div class="form-group">
                <label for="title">Title</label>
                <input type="text" id="title" name="title" placeholder="Task title..." required
                       maxlength="50">
            </div>
            <div class="form-group">
                <label for="description">Description</label>
                <textarea id="description" name="description" placeholder="What needs to be done?"
                          maxlength="350" rows="4"></textarea>
            </div>
            <div class="form-row">
                <div class="form-group">
                    <label for="ablaufdatum">Due Date</label>
                    <input type="date" id="ablaufdatum" name="duedatum">
                </div>
                <div class="form-group">
                    <label for="kategorie">Category</label>
                    <select id="kategorie" name="kategorie">
                        <option value="" disabled selected>Select category...</option>
                        <c:forEach var="category" items="${categories}">
                            <option value="${category.kategorieID}">
                                    ${category.name}
                            </option>
                        </c:forEach>
                    </select>
                </div>
            </div>
            <div class="form-actions">
                <a href="${pageContext.request.contextPath}/sites/task.jsp"
                   class="btn-cancel">Cancel</a>
                <button type="submit" class="btn-submit">Create Task</button>
            </div>
        </form>
    </div>
    <a class="addBtn" href="${pageContext.request.contextPath}/sites/addCategory.jsp">+</a>
</main>
</body>
</html>