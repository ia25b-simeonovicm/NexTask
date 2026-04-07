<%@ page import="org.example.nextask.model.User" %>
<%@ page isELIgnored="false" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%
    User user = (User) session.getAttribute("user");
    int userid = user.getUserID();
%>

<!DOCTYPE html>
<html lang="de">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>NexTask – Settings</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/tasks.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/settings.css">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link href="https://fonts.googleapis.com/css2?family=DM+Sans:wght@400;500;600;700&display=swap" rel="stylesheet">
</head>
<body>

<!-- ===== NAVBAR ===== -->
<nav class="navbar">
    <div class="nav-brand"></div>
    <nav>
        <a href="${pageContext.request.contextPath}/sites/overview.jsp" class="nav-item">Overview</a>
        <a href="${pageContext.request.contextPath}/sites/task.jsp" class="nav-item">Tasks</a>
        <a href="${pageContext.request.contextPath}/sites/addCategory.jsp" class="nav-item">Categories</a>
        <a href="${pageContext.request.contextPath}/sites/settings.jsp" class="nav-item active">${sessionScope.user.username}</a>
    </nav>
</nav>

<!-- ===== MAIN CONTENT ===== -->
<main class="settings-main">
    <div class="settings-wrapper">

        <!-- 1. PROFILE SECTION -->
        <form action="${pageContext.request.contextPath}/update" method="post">
            <section class="settings-section">
                <h2 class="section-label">Profile</h2>
                <div class="settings-card">
                    <div class="card-row">
                        <div class="field-group">
                            <input type="hidden" name="action" value="profile">
                            <label class="field-label">Username</label>
                            <input type="text" class="field-input" name="username"
                                   value="${sessionScope.user.username}" placeholder="Dein Username">
                        </div>
                        <div class="field-group">
                            <label class="field-label">E-Mail</label>
                            <input type="email" class="field-input" name="email"
                                   value="${sessionScope.user.email}" placeholder="deine@email.com">
                        </div>
                    </div>
                    <!-- Button zum Speichern des Profils -->
                    <div class="card-actions">
                        <button type="submit" class="btn-save">Profil Speichern</button>
                    </div>
                </div>
            </section>
        </form>

        <!-- 2. ACCOUNT / PASSWORT SECTION -->
        <form action="${pageContext.request.contextPath}/update" method="post">
            <section class="settings-section">
                <h2 class="section-label">Passwort ändern</h2>
                <div class="settings-card">
                    <div class="card-row">
                        <div class="field-group">
                            <input type="hidden" name="action" value="password">
                            <label class="field-label">Altes Passwort</label>
                            <input type="password" class="field-input" name="old_password" placeholder="Altes Passwort">
                        </div>
                        <div class="field-group">
                            <label class="field-label">Neues Passwort</label>
                            <input type="password" class="field-input" name="newpassword" placeholder="Neues Passwort">
                        </div>
                    </div>
                    <c:if test="${not empty error}">
                        <div class="error-message">
                                ${error}
                        </div>
                    </c:if>
                    <!-- Button zum Speichern des Passworts -->
                    <div class="card-actions">
                        <button type="submit" class="btn-save">Passwort Speichern</button>
                    </div>
                </div>
            </section>
        </form>

        <!-- 3. GEFAHRENZONE (Ausloggen & Löschen) -->
        <section class="settings-section">
            <h2 class="section-label">Weitere Optionen</h2>
            <div class="settings-card">
                <div class="card-actions" style="justify-content: flex-start; gap: 15px;">
                    <!-- Ausloggen -->
                    <a href="${pageContext.request.contextPath}/logout" class="btn-danger" style="text-decoration: none; text-align: center;">Ausloggen</a>

                    <!-- Account Löschen Formular -->
                    <form action="${pageContext.request.contextPath}/delete" method="post" style="margin: 0;">
                        <input type="hidden" name="action" value="deleteAccount">
                        <button type="submit" class="btn-danger">Account löschen</button>
                    </form>
                </div>
            </div>
        </section>

    </div>
</main>

</body>
</html>