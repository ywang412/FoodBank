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

    <form id="command" action="AddRequest" method="get">
    <table border="1">
            <tr>
                <td>itemName</td>
                <td>numberOfUnits</td>
                <td>storageType</td>
                <td>itemType</td>
                <td>foodCategory</td>
                <td>supplyCategory</td>
                <td>expirationDate</td>
                <td>foodBank</td>
                <td>Request</td>
	    </tr>
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
                <td><c:if test="${item.onSite == '0'}">
      <input type="hidden" name="username" value="emp1">
      <input type="hidden" name="itemName" value="${item.itemName}">
      <input type="hidden" name="foodBank" value="${item.foodBank}">
      <input type="text" name="count" value="0">

</c:if><c:if test="${item.onSite>0}">Already on site</c:if></td>
            </tr>

        </c:forEach>
    </table>
      <input type="submit" value="Request">
    </form:form>
                    <form method="post" action="/login">
                        <button type="submit" align="center">Return to User Dashboard</button>
                    </form>



</div>
</body>
</html>
