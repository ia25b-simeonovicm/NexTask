<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%
    class Todo {
        public String title;
        public String desc;
        public String category;
        public Todo(String title, String desc, String category) {
            this.title = title;
            this.desc = desc;
            this.category = category;
        }
    }

    List<Todo> todos = new ArrayList<>();
    todos.add(new Todo("Meeting Prep", "Folien für das Quartalsmeeting vorbereiten.", "work"));
    todos.add(new Todo("Sport", "Heute Abend 5km laufen gehen.", "personal"));
    todos.add(new Todo("Code Review", "Pull Request #42 ansehen.", "work"));
    todos.add(new Todo("App Idee", "Konzept für das neue Feature skizzieren.", "ideas"));
    todos.add(new Todo("Kunden anrufen", "Rückruf bei der Schmidt GmbH.", "work"));

    todos.add(new Todo("Design Sprint", "Inspirationen für das neue Layout sammeln.", "ideas"));
    todos.add(new Todo("Doku Update", "API Endpoint Dokumentation aktualisieren.", "work"));
    todos.add(new Todo("Bug Fixing", "Login Fehler auf Staging beheben.", "work"));
    todos.add(new Todo("Refactoring", "Datenbank-Klasse aufräumen.", "work"));
    todos.add(new Todo("Server Ausfall", "Dringend Logs prüfen!", "urgent"));

    todos.add(new Todo("Deadline", "Projekt bis morgen 12 Uhr abschließen.", "urgent"));
    todos.add(new Todo("Team Sync", "Tägliches Stand-up Meeting.", "work"));
    todos.add(new Todo("Tickets", "Neue Jira-Tickets sortieren.", "misc"));
    todos.add(new Todo("Feedback lesen", "Kundenfeedback auswerten.", "work"));
    todos.add(new Todo("Deploy", "Release v1.2 auf Prod pushen.", "work"));
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
