<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="org.example.nextask.model.User" %>
<%@ page import="org.example.nextask.model.Kategorie" %>
<%@ page import="org.example.nextask.dao.KategorieDAO" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%-- Verweist auf Login page falls nicht eingeloggt --%>
<c:if test="${empty sessionScope.user}">
    <c:redirect url="${pageContext.request.contextPath}/index.jsp"/>
</c:if>
<%
    User user = (User) session.getAttribute("user");
    KategorieDAO katDao = new KategorieDAO();
    request.setAttribute("categories", katDao.getAllKategorieByUser(user.getUserID()));
%>

<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Kategorien</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/tasks.css">
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/css/categories.css">
</head>
<body>
<header class="navbar">
    <nav>
        <a href="${pageContext.request.contextPath}/sites/addTodo.jsp"
           class="nav-item">← Back</a>
        <a href="#" class="nav-item active">Kategorien</a>
        <span class="nav-item">👤 ${sessionScope.user.username}</span>
    </nav>
</header>
<main>
    <div class="form-container">
        <h1 class="form-title">Kategorien</h1>
        <form action="${pageContext.request.contextPath}/category" method="post"
              class="todo-form">
            <div class="form-row">
                <div class="form-group">
                    <label for="name">Name</label>
                    <input type="text" id="name" name="name"
                           placeholder="Kategoriename..." required maxlength="50">
                </div>
                <div class="form-group form-group--color">
                    <label for="color">Farbe</label>
                    <input type="color" id="color" name="color" value="#3AAF9F">
                </div>
            </div>
            <div class="form-actions">
                <button type="submit" class="btn-submit">Erstellen</button>
            </div>
        </form>
        <h2 class="section-title">Deine Kategorien</h2>
        <div class="kategorie-list">
            <c:choose>
                <c:when test="${empty categories}">
                    <p class="empty-msg">Noch keine Kategorien erstellt.</p>
                </c:when>
                <c:otherwise>
                    <c:forEach var="category" items="${categories}">
                        <div class="kategorie-item">
                            <div class="kategorie-color"
                                 style="background: ${category.color}">
                                <span class="kategorie-name">${category.name}</span>
                                <form action="${pageContext.request.contextPath}/deleteCategory"
                                      method="post" class="delete-form">
                                    <input type="hidden" name="kategorieId"
                                           value="${category.kategorieID}">
                                    <button type="submit"
                                            class="btn-delete">x
                                    </button>
                                </form>
                            </div>
                        </div>
                    </c:forEach>
                </c:otherwise>
            </c:choose>
        </div>
    </div>
</main>
</body>
</html>