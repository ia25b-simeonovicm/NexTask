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

    KategorieDAO dao = new KategorieDAO();
    List<Kategorie> categories = dao.getAllKategorieByUser(user.getUserID());
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
        <a href="#" class="nav-item active">New Task</a>
        <span class="nav-item">👤 <%= user.getUsername() %></span>
    </nav>
</header>

<main>
    <div class="form-container">
        <h1 class="form-title">New Task</h1>
        <form action="${pageContext.request.contextPath}/todo" method="post" class="todo-form">

            <div class="form-group">
                <label for="title">Title</label>
                <input type="text" id="title" name="title" placeholder="Task title..." required maxlength="50">
            </div>

            <div class="form-group">
                <label for="description">Description</label>
                <textarea id="description" name="description" placeholder="What needs to be done?" maxlength="350" rows="4"></textarea>
            </div>

            <div class="form-row">
                <div class="form-group">
                    <label for="ablaufdatum">Due Date</label>
                    <input type="date" id="ablaufdatum" name="duedatum">
                </div>

                <div class="form-group">
                    <label for="kategorie">Category</label>
                    <select id="kategorie" name="kategorieId">
                        <option value="" disabled selected>Select category...</option>
                        <% for (Kategorie category : categories) { %>
                        <option value="<%= category.getKategorieID() %>"
                                style="background-color: <%= category.getColor() %>">
                            <%= category.getName() %>
                        </option>
                        <% } %>
                    </select>
                </div>
            </div>

            <div class="form-actions">
                <a href="${pageContext.request.contextPath}/sites/task.jsp" class="btn-cancel">Cancel</a>
                <button type="submit" class="btn-submit">Create Task</button>
            </div>

        </form>
    </div>
</main>

</body>
</html>