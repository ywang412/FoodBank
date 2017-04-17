<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Phil
  Date: 4/17/2017
  Time: 2:45 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Bank Request List</title>
</head>
<body>

<h1>Items Requested From Food Bank #${foodBank}</h1>


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






    <form method="post" action="/login">
        <button type="submit" align="center">Return to User Dashboard</button>
    </form>



    </div>


</body>
</html>
