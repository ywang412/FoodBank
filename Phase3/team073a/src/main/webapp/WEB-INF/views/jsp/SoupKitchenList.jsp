<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Phil
  Date: 4/13/2017
  Time: 10:22 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>ASACS Soup Kitchen List</title>
</head>
<body>
<div align="center">
    <h1>Soup Kitchens</h1>

    <h2>Count is: ${count}</h2>

    <table border="1">
        <c:forEach items="${lists}" var="soupkitchen">
            <tr>
                <td>${soupkitchen.soupKitchenId}</td>
                <td>${soupkitchen.descriptionString}</td>
                <td>${soupkitchen.hours}</td>
                <td>${soupkitchen.conditionsForUse}</td>
                <td>${soupkitchen.availableSeats}</td>
            </tr>
        </c:forEach>

    </table>


</div>
</body>
</html>
