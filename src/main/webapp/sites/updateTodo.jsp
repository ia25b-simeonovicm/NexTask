<%@ page isELIgnored="false" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page import="org.example.nextask.model.User" %>
<%@ page import="org.example.nextask.dao.KategorieDAO" %>


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
    <title>ToDo bearbeiten</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/tasks.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/addTodo.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/updateTodo.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/categories.css">
</head>
<body>
<header class="navbar">
    <nav>
        <a href="${pageContext.request.contextPath}/sites/task.jsp" class="nav-item">← Back</a>
        <a href="${pageContext.request.contextPath}/sites/overview.jsp" class="nav-item">Overview</a>
        <a href="${pageContext.request.contextPath}/sites/task.jsp" class="nav-item active">Tasks</a>
        <a href="${pageContext.request.contextPath}/category" class="nav-item">Categories</a>
        <a href="${pageContext.request.contextPath}/sites/settings.jsp"
           class="nav-item">${sessionScope.user.username}</a>
    </nav>
</header>
<main>
    <div class="form-container">
        <h1 class="form-title">ToDo bearbeiten</h1>

        <form action="${pageContext.request.contextPath}/todoUpdate" method="post" class="todo-form">
            <input type="hidden" name="toDoID" value="${todo.toDoID}">

            <div class="form-row">
                <div class="form-group" style="flex: 2;">
                    <label for="title">Titel</label>
                    <input type="text" id="title" name="title" value="${todo.title}" required maxlength="50">
                </div>
                <div class="form-group" style="flex: 1;">
                    <label for="ablaufdatum">Fällig am</label>
                    <input type="date" id="ablaufdatum" name="ablaufdatum" value="${todo.ablaufdatum}">
                </div>
            </div>

            <div class="form-row">
                <div class="form-group">
                    <label for="kategorieID">Kategorie</label>
                    <select id="kategorieID" name="kategorieID" class="form-select">
                        <option value="">-- Keine Kategorie --</option>
                        <c:forEach var="cat" items="${categories}">
                            <option value="${cat.kategorieID}"
                                ${todo.kategorie != null && cat.kategorieID == todo.kategorie.kategorieID ? 'selected' : ''}>
                                    ${cat.name}
                            </option>
                        </c:forEach>
                    </select>
                </div>

                <div class="form-group form-group--checkbox">
                    <label class="checkbox-label">
                        Status: Erledigt
                        <input type="checkbox" name="isDone" value="true" ${todo.done ? 'checked' : ''}>
                    </label>
                </div>
            </div>

            <div class="form-group">
                <label for="description">Beschreibung</label>
                <textarea id="description" name="description" rows="4" maxlength="350">${todo.description}</textarea>
            </div>

            <div class="form-actions" style="margin-top: 10px;">
                <a href="${pageContext.request.contextPath}/sites/task.jsp" class="btn-cancel">Abbrechen</a>
                <button type="submit" class="btn-submit">Speichern</button>
            </div>
        </form>
    </div>
</main>
</body>
</html>