<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="org.example.nextask.model.ToDo" %>
<%@ page import="org.example.nextask.dao.ToDoDAO" %>
<%@ page import="org.example.nextask.dao.KategorieDAO" %>
<%@ page import="org.example.nextask.model.Kategorie" %>
<%@ page import="org.example.nextask.model.User" %>
<%
    User user = (User) session.getAttribute("user");

    if (user == null) {
        response.sendRedirect(request.getContextPath() + "/index.jsp");
        return;
    }


    ToDoDAO TodoDao = new ToDoDAO();
    KategorieDAO KatDao = new KategorieDAO();

    int userid = user.getUserID();

    List<ToDo> todos = TodoDao.getAllToDoByUser(userid);
    List<Kategorie> categories = KatDao.getAllKategorieByUser(userid);
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
    <% for (Kategorie category : categories) { %>
    .category-<%= category.getName().replace(" ", "-") %> {
        background: <%= category.getColor() %>;
    }
    <% } %>
</style>
<body>
<header class="navbar">
    <nav>
        <a href="#" class="nav-item">Overview</a>
        <a href="#" class="nav-item active">Tasks</a>
        <span class="nav-item">👤 <%= user.getUsername() %></span>
    </nav>
</header>
<main class="grid-container" id="todo-container">
    <% if (!todos.isEmpty()) { %>
    <% for (ToDo todo : todos) { %>
    <div class="todo-card category-<%= todo.getKategorie().getName().replace(" ", "-") %>">
        <div class="todo-content">
            <div class="todo-category-badge"><%= todo.getKategorie().getName() %></div>
            <div class="todo-title"><%= todo.getTitle() %></div>
            <div class="todo-desc"><%= todo.getDescription() %></div>
        </div>
    </div>
    <% } %>
    <% } else { %>
    <div>Keine Todos erstellt...</div>
    <% } %>
    <a class="addBtn" href="${pageContext.request.contextPath}/sites/addTodo.jsp">+</a>
</main>
</body>
</html>
