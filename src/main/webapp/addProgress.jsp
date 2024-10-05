<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add Progress</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            color: #333;
            padding: 20px;
            text-align: center;
        }
        h1 {
            color: #2c3e50;
        }
        form {
            display: inline-block;
            text-align: left;
            margin-top: 10px;
            background-color: white;
            padding: 30px; /* Increased padding for larger form */
            border-radius: 10px;
            box-shadow: 0 2px 5px rgba(0,0,0,0.1);
            width: 400px; /* Fixed width for a larger form */
        }
        input[type="text"], input[type="date"], select {
            width: calc(100% - 20px);
            padding: 10px;
            margin: 10px 0; /* Increased margin for better spacing */
            border: 1px solid #ccc;
            border-radius: 5px;
        }
        input[type="submit"] {
            background-color: #3498db;
            color: white;
            border: none;
            padding: 10px 15px;
            cursor: pointer;
            border-radius: 5px;
            width: 100%; /* Full width for the submit button */
        }
        input[type="submit"]:hover {
            background-color: #2980b9;
        }
        label {
            margin-right: 10px;
        }
    </style>
</head>
<body>
<h1>Add Progress</h1>
<form action="progress" method="post">
    <input type="hidden" name="action" value="add">
    User ID: <input type="text" name="userId" required pattern="\d+" title="User ID must be a number"><br>
    Module ID: <input type="text" name="moduleId" required pattern="\d+" title="Module ID must be a number"><br>
    <label for="progressStatus">Progress Status:</label>
    <select name="progressStatus" id="progressStatus" required>
        <option value="">Select a status</option>
        <option value="beginner">Beginner</option>
        <option value="starter">Starter</option>
        <option value="average">Average</option>
        <option value="good">Good</option>
        <option value="excellent">Excellent</option>
    </select><br>
    Completion Date: <input type="date" name="completionDate" required><br>
    Score: <input type="text" name="score" required pattern="^[0-9]{1,2}$" title="Score must be a number between 0 and 999"><br>
    Attempts: <input type="text" name="attempts" required pattern="\d+" title="Attempts must be a number"><br>
    <input type="submit" value="Add Progress">
</form>
</body>
</html>
