<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Phil
  Date: 4/13/2017
  Time: 11:49 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>ASACS Request List</title>
</head>
<body>
<div align="center">
    <h1>Requests</h1>


    <table border="1">
            <tr>
                <td>userName</td>
                <td>itemName</td>
                <td>unitsRequested</td>
                <td>unitsFulfilled</td>
                <td>requestDate</td>
                <td>requestStatus</td>
            </tr>
        <c:forEach items="${lists}" var="request">
            <tr>
                <td>${request.userName}</td>
                <td>${request.itemName}</td>
                <td>${request.unitsRequested}</td>
                <td>${request.unitsFulfilled}</td>
                <td>${request.requestDate}</td>
                <td>${request.requestStatus}</td>
            </tr>
        </c:forEach>

    </table>

    <tr>
        <td >
            <form method="post" action="/login">
                <button type="submit" align="center">Return to User Dashboard</button>
            </form>
        </td>

    </tr>


</div>
</body>
</html>
