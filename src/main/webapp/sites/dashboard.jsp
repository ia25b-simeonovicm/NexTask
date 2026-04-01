<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Dashboard</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css">
</head>
<body>
<div>
    <div class="NavBar"> <!---------------------------NAVBAR------------------------------------------------->
        <ul>
            <li><a href="${pageContext.request.contextPath}/sites/dashboard.jsp">Overview</a></li>
            <li><a href="${pageContext.request.contextPath}/sites/task.jsp">Task</a></li>
            <li><a href="${pageContext.request.contextPath}/sites/settings.jsp">Icon Settings</a></li>
        </ul>
    </div>
    <div class="todaystask">

    </div>
    <div class="kategorietask">

    </div>

    <div class="kalender"></div>
</div>
</body>
</html>
