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
    <title>ASACS Item List</title>
</head>
<body>
<div align="center">
    <h1>Items</h1>


    <table border="1">
        <c:forEach items="${lists}" var="item">
            <tr>
                <td>${item.itemName}</td>
                <td>${item.numberOfUnits}</td>
                <td>${item.storageType}</td>
                <td>${item.itemType}</td>
                <td>${item.foodCategory}</td>
                <td>${item.supplyCategory}</td>
                <td>${item.expirationDate}</td>
                <td>${item.foodBank}</td>
            </tr>
        </c:forEach>

    </table>


</div>
</body>
</html>
