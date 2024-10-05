<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Progress Management</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding_left: 20px;
        }
        h1 {
            text-align: center;
            color: #333;
        }
        a {
            display: inline-block;
            margin: 40px;
            padding: 10px 20px;
            background-color: #007bff;
            color: white;
             text-align: center;
            text-decoration: none;
            border-radius: 5px;
        }
        .flex-container {
            display: flex;
            justify-content: center; /* Centers children horizontally */
        }
        a:hover {
            background-color: #0056b3;
        }
    </style>
</head>
<body>
    <h1>Progress Management </h1>
    <div class="flex-container">
    <a href="addProgress.jsp">Add Progress</a>
    <a href="progress?action=view">View All Progress</a>
    </div>
</body>
</html>
