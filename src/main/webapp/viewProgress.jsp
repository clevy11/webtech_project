<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="MODEL.Progress" %>
<!DOCTYPE html>
<html>
<head>
    <title>View Progress</title>
    <style>
        table {
            width: 100%;
            border-collapse: collapse;
        }
        th, td {
            padding: 10px;
            border: 1px solid #ddd;
            text-align: center;
        }
        th {
            background-color: #f2f2f2;
        }
        a {
            margin: 0 5px;
            text-decoration: none;
            color: #007bff;
        }
        a:hover {
            text-decoration: underline;
        }
    </style>
</head>
<body>
    <h1>All Progress Records</h1>

    <%
        // Retrieve the progressList from the request attribute
        List<Progress> progressList = (List<Progress>) request.getAttribute("progressList");



    %>

    <table>
        <tr>
            <th>Progress ID</th>
            <th>User ID</th>
            <th>Module ID</th>
            <th>Status</th>
            <th>Completion Date</th>
            <th>Score</th>
            <th>Attempts</th>
            <th>Actions</th>
        </tr>
        <%
            if (progressList != null && !progressList.isEmpty()) {
                for (Progress progress : progressList) {
        %>
        <tr>
            <td><%= progress.getProgressId() %></td>
            <td><%= progress.getUserId() %></td>
            <td><%= progress.getModuleId() %></td>
            <td><%= progress.getProgressStatus() %></td>
            <td><%= progress.getCompletionDate() %></td>
            <td><%= progress.getScore() %></td>
            <td><%= progress.getAttempts() %></td>
            <td>
                <a href="progress?action=update&id=<%= progress.getProgressId() %>">Edit</a>
                <a href="progress?action=delete&id=<%= progress.getProgressId() %>" onclick="return confirm('Are you sure you want to delete this record?');">Delete</a>
            </td>
        </tr>
        <%
                }
            } else {
        %>
        <tr>
            <td colspan="8">No progress records found.</td>
        </tr>
        <%
            }
        %>
    </table>
    <br>
    <a href="index.jsp">Back to Home</a>
</body>
</html>
