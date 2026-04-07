<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
    <div class="nav-brand">
    </div>
    <nav>
        <a href="${pageContext.request.contextPath}/sites/overview.jsp" class="nav-item">Overview</a>
        <a href="${pageContext.request.contextPath}/sites/task.jsp" class="nav-item">Tasks</a>
        <a href="${pageContext.request.contextPath}/sites/addCategory.jsp" class="nav-item">Categories</a>
        <a href="${pageContext.request.contextPath}/sites/settings.jsp"
           class="nav-item active">${sessionScope.user.username}</a>
    </nav>
</nav>

<!-- ===== MAIN CONTENT ===== -->
<main class="settings-main">
    <div class="settings-wrapper">

        <!-- PROFILE SECTION -->
        <form action="${pageContext.request.contextPath}/update" method="post">
            <section class="settings-section">
                <h2 class="section-label">Profile</h2>
                <div class="settings-card">
                    <div class="card-row">
                        <div class="field-group">
                            <input type="hidden" name="action" value="profile">
                            <label class="field-label">Username</label>
                            <input type="text" class="field-input" name="username"
                                   value="${not empty sessionScope.user ? sessionScope.user.username : ''}"
                                   placeholder="Dein Username">
                        </div>
                        <div class="field-group">
                            <label class="field-label">E-Mail</label>
                            <input type="email" class="field-input" name="email"
                                   value="${not empty sessionScope.user ? sessionScope.user.email : ''}"
                                   placeholder="deine@email.com">
                        </div>
                    </div>
                    <div class="card-actions">
                        <button class="btn-danger">Ausloggen</button>
                        <button class="btn-save">Speichern</button>
                    </div>
                </div>
            </section>
        </form>
        <form action="${pageContext.request.contextPath}/update" method="post">
            <!-- ACCOUNT SECTION -->
            <section class="settings-section">
                <h2 class="section-label">Account</h2>
                <div class="settings-card">
                    <div class="card-row">
                        <div class="field-group">
                            <input type="hidden" name="action" value="password">
                            <label class="field-label">Passwort ändern</label>
                            <input type="password" class="field-input" placeholder="Altes Passwort" name="old_password">
                        </div>
                        <div class="field-group">
                            <label class="field-label">Bestätigen</label>
                            <input type="password" class="field-input" placeholder="Neues Passwort" name="newpassword">
                        </div>
                    </div>
                    <div class="card-actions">
                        <button class="btn-save">Speichern</button>
                        <button class="btn-danger">Account löschen</button>
                    </div>
                </div>
            </section>
        </form>
    </div>
</main>

</body>
</html>
