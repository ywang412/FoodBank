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

    <title>RoomWaitList</title>
</head>
<body>

<div align="center">
    <h1>ASACS Room Wait List </h1>

    <h2>Shelter Name is: ${shelter_ds}</h2>

    <table border="1">
        <tr>
            <td>Position</td>
            <td>Client Name</td>
            <td>client Id</td>
            <td>HeadOfHousehold</td>
        </tr>
        <c:forEach items="${allWaitlist}" var="waitlist">
            <tr>
                <td>${waitlist.position}</td>
                <td>${waitlist.fullName}</td>
                <td>${waitlist.clientId}</td>
                <td>${waitlist.headOfHousehold}</td>
            </tr>
        </c:forEach>




    </table>

    <td>

        <a href="<c:url value='/shelterform?username=${userName}&siteId=${siteId}' />" >Return to Service</a>

    </td>

    <tr>
        <td colspan="2" align="center"><input type="submit" value="Logout"></td>
    </tr>


</div>
</body>
</html>