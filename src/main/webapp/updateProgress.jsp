<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="MODEL.Progress" %>
<%
    Progress progress = (Progress) request.getAttribute("progress");
%>
<html>
<head>
    <title>Update Progress</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 20px;
        }
        h1 {
            text-align: center;
            color: #333;
        }
        form {
            max-width: 400px;
            margin: auto;
            background: white;
            padding: 20px;
            border-radius: 5px;
            box-shadow: 0 0 10px rgba(0,0,0,0.1);
        }
        input[type="text"], input[type="date"] {
            width: 100%;
            padding: 10px;
            margin: 10px 0;
            border: 1px solid #ccc;
            border-radius: 5px;
        }
        input[type="submit"] {
            background-color: #007bff;
            color: white;
            border: none;
            padding: 10px;
            border-radius: 5px;
            cursor: pointer;
            width: 100%;
        }
        input[type="submit"]:hover {
            background-color: #0056b3;
        }
    </style>
</head>
<body>    <h1>Update Progress</h1>
    <form action="progress" method="post">
        <input type="hidden" name="action" value="update">
        <input type="hidden" name="progressId" value="<%= progress.getProgressId() %>">
        User ID: <input type="text" name="userId" value="<%= progress.getUserId() %>" required>
        Module ID: <input type="text" name="moduleId" value="<%= progress.getModuleId() %>" required>
        Progress Status: <input type="text" name="progressStatus" value="<%= progress.getProgressStatus() %>" required>
        Completion Date: <input type="date" name="completionDate" value="<%= progress.getCompletionDate() %>" required>
        Score: <input type="text" name="score" value="<%= progress.getScore() %>" required>
        Attempts: <input type="text" name="attempts" value="<%= progress.getAttempts() %>" required>
        <input type="submit" value="Update Progress">
    </form>
</body>
</html>

