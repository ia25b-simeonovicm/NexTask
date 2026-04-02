<%@ page import="org.example.nextask.model.User" %>
<%@ page import="org.example.nextask.model.Kategorie" %>
<%@ page import="org.example.nextask.dao.KategorieDAO" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    User user = (User) session.getAttribute("user");

    if (user == null) {
        response.sendRedirect(request.getContextPath() + "/index.jsp");
        return;
    }

    KategorieDAO katDao = new KategorieDAO();
    List<Kategorie> categories = katDao.getAllKategorieByUser(user.getUserID());
%>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Kategorien</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/tasks.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/categories.css">
</head>
<body>

<header class="navbar">
    <nav>
        <a href="${pageContext.request.contextPath}/sites/addTodo.jsp" class="nav-item">← Back</a>
        <a href="#" class="nav-item active">Kategorien</a>
        <span class="nav-item">👤 <%= user.getUsername() %></span>
    </nav>
</header>

<main>
    <div class="form-container">
        <h1 class="form-title">Kategorien</h1>

        <form action="${pageContext.request.contextPath}/category" method="post" class="todo-form">
            <div class="form-row">
                <div class="form-group">
                    <label for="name">Name</label>
                    <input type="text" id="name" name="name" placeholder="Kategoriename..." required maxlength="50">
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
            <% if (categories.isEmpty()) { %>
            <p class="empty-msg">Noch keine Kategorien erstellt.</p>
            <% } else {
                for (Kategorie kat : categories) { %>
            <div class="kategorie-item">
                <div class="kategorie-color" style="background: <%= kat.getColor() %>"></div>
                <span class="kategorie-name"><%= kat.getName() %></span>
                <form action="${pageContext.request.contextPath}/deleteKategorie" method="post" class="delete-form">
                    <input type="hidden" name="kategorieId" value="<%= kat.getKategorieID() %>">
                    <button type="submit" class="btn-delete">✕</button>
                </form>
            </div>
            <% } } %>
        </div>
    </div>
</main>

</body>
</html>