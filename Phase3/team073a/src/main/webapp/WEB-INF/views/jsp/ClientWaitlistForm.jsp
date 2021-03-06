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

    <title>ClientRoomWaitList</title>
</head>
<body>

<div align="center">
    <h1>ASACS Room Wait List for Client </h1>


    <table border="1">
        <tr>
            <td>Position</td>
            <td>client Id</td>
            <td>Shelter Name</td>

        </tr>
        <c:forEach items="${clientWaitlist}" var="waitlist">
            <tr>
                <td>${waitlist.position}</td>
                <td>${waitlist.clientId}</td>
                <td>${waitlist.description}</td>
            </tr>
        </c:forEach>




    </table>

    <tr>
        <form method="get" action="/addClientWaitlist">
            <td colspan="2" align="center"><input type="submit" value="add client from Waitlist for this shelter/site"></td>

        </form>
    </tr>

    <tr>
        <form method="get" action="/removeClientWaitlist">
            <td colspan="2" align="center"><input type="submit" value="Remove client from Waitlist for this shelter/site"></td>

        </form>
    </tr>



    <form method="get" action="/ClientSearchForm">
        <td colspan="2" align="center"><input type="submit" value="Return to Client Search"></td>

    </form>

    <br>
    <tr>
        <form method="post" action="/invalidate">
            <td colspan="2" align="center"><input type="submit" value="Logout"></td>

        </form>
    </tr>


</div>

<br>
<div align="center">
    <form method="post" action="SiteInfo">
        <button type="submit" align="center">Return To Site</button>
    </form>
</div>
</body>
</html>