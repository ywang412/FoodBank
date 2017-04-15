<%--
  Created by IntelliJ IDEA.
  User: swengineer
  Date: 4/14/17
  Time: 9:16 PM
  To change this template use File | Settings | File Templates.
--%>


<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>

    <title>AvailableRoomBunk</title>
</head>
<body>
<div align="center">

    <h1>ASACS Available Rooms and Bunks </h1>

    <c:if test="${not empty allRoomBunk}">

        <ul>
            <c:forEach var="roomBunkCount" items="${allRoomBunk}">
                <li>Site Name: ${roomBunkCount.siteName} /Address: ${roomBunkCount.siteName} /Contact: ${roomBunkCount.contactNumber}  /Hours: ${roomBunkCount.hours}  /Type: ${roomBunkCount.roomBunkTypeString} /Count: ${roomBunkCount.roomBunkCount}</li>

            </c:forEach>
        </ul>


    </c:if>


    <%--<form:form action="login" method="post">--%>
        <%--<table>--%>

            <%--<tr>--%>
                <%--<th>Site Name</th>--%>
                <%--<th>Phone number</th>--%>
                <%--<th>Location</th>--%>
                <%--<th>Rooms</th>--%>
                <%--<th>Bunks(Male)</th>--%>
                <%--<th>Bunks(Female)</th>--%>
                <%--<th>Bunks(Mixed)</th>--%>
            <%--</tr>--%>


            <%--<tr>--%>
                <%--<th>${foodBank.foodBankId}</th>--%>
                <%--<th>${foodBank.descriptionString}</th>--%>
            <%--</tr>--%>
            <%--<tr>--%>
                <%--<th>${foodPantry.foodPantryId}</th>--%>
                <%--<th>${foodPantry.descriptionString}</th>--%>
                <%--<th>${foodPantry.hours}</th>--%>
                <%--<th>${foodPantry.conditionsForUse}</th>--%>
            <%--</tr>--%>
            <%--<tr>--%>
                <%--<th>${soupKitchen.soupKitchenId}</th>--%>
                <%--<th>${soupKitchen.descriptionString}</th>--%>
                <%--<th>${soupKitchen.hours}</th>--%>
                <%--<th>${soupKitchen.conditionsForUse}</th>--%>
            <%--</tr>--%>
            <%--<tr>--%>
                <%--<th>${shelter.shelterId}</th>--%>
                <%--<th>${shelter.descriptionString}</th>--%>
                <%--<th>${shelter.hours}</th>--%>
                <%--<th>${shelter.conditionsForUse}</th>--%>

            <%--</tr>--%>

            <%--<td><a href="<c:url value='/foodpantryform?username=${user.userName}&siteId=${siteInfo.siteId}' />" >Go to FoodPantry</a></td>--%>


            <%--<tr>--%>
                <%--<td colspan="2" align="center"><input type="submit" value="Return to User Dashboard"></td>--%>
            <%--</tr>--%>
            <%--<tr>--%>
                <%--<td colspan="2" align="center"><input type="submit" value="Logout"></td>--%>
            <%--</tr>--%>
        <%--</table>--%>
    <%--</form:form>--%>
</div>
</body>
</html>