import org.example.nextask.dao.ToDoDAO;
import org.example.nextask.dao.ToDoDAO;
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%
    List<Todo> todos =
%>
<!DOCTYPE html>
<html lang="de">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>To-Do Template (JSP)</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/tasks.css">
    <link href="https://fonts.googleapis.com/css2?family=Inter:wght@400;600;700&display=swap" rel="stylesheet">
</head>
<stlye>

</stlye>
<body>
<header class="navbar">
    <nav>
        <a href="#" class="nav-item">Overview</a>
        <a href="#" class="nav-item active">Tasks</a>
        <a href="#" class="nav-item">Icon</a>
    </nav>
</header>
<main class="grid-container" id="todo-container">
    <% for(Todo todo : todos) { %>
    <div class="todo-card category-<%= todo.category %>">
        <div class="todo-content">
            <div class="todo-category-badge"><%= todo.category %></div>
            <div class="todo-title"><%= todo.title %></div>
            <div class="todo-desc"><%= todo.desc %></div>
        </div>
    </div>
    <% } %>
</main>
</body>
</html>
