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
    <title>ASACS Shelter List</title>
</head>
<body>
<div align="center">
    <h1>Shelters</h1>

    <h2>Count is: ${count}</h2>

    <table border="1">
        <c:forEach items="${lists}" var="shelters">
            <tr>
                <td>${shelters.shelterId}</td>
                <td>${shelters.descriptionString}</td>
                <td>${shelters.hours}</td>
                <td>${shelters.conditionsForUse}</td>
                <td>${shelters.availableBunks}</td>
                <td>${shelters.availableRooms}</td>
            </tr>
        </c:forEach>

    </table>


</div>
</body>
</html>
