<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.ArrayList, java.util.List" %>
<%@ page import="org.example.nextask.model.User" %>
<%@ page import="org.example.nextask.model.ToDo" %>
<%@ page import="org.example.nextask.dao.UserDAO" %>
<%@ page import="org.example.nextask.dao.ToDoDAO" %>
<%@ page import="org.example.nextask.dao.KategorieDAO" %>
<%@ page import="org.example.nextask.model.Kategorie" %>
<%
    UserDAO UserDao = new UserDAO();
    ToDoDAO TodoDao = new ToDoDAO();
    KategorieDAO KatDao = new KategorieDAO();

    List<ToDo> todos = TodoDao.getAllToDo();
    List<ToDo> personalTodo = new ArrayList<>();
    User user = UserDao.searchUserByUsername();
    for (ToDo todo : todos) {
        if (todo.getUser() == user) {
            personalTodo.add(todo);
        }
    }

    List<Kategorie> categories = KatDao.getAllCategories();
    List<Kategorie> personalCategorie = new ArrayList<>();
    for (Kategorie category : categories) {
        if (category.getUser() == user) {
            personalCategorie.add(category);
        }
    }
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
    <% for (Kategorie category : personalCategorie) { %>
        .category-<%= category.getName() %> {
        background: <%= category.getColor() %>
    <% } %>
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
    <% for (ToDo todo : personalTodo) { %>
    <div class="todo-card category-<%= todo.getKategorie() %>">
        <div class="todo-content">
            <div class="todo-category-badge"><%= todo.getKategorie() %>
            </div>
            <div class="todo-title"><%= todo.getKategorie() %>
            </div>
            <div class="todo-desc"><%= todo.getDescription() %>
            </div>
        </div>
    </div>
    <% } %>
</main>
</body>
</html>
