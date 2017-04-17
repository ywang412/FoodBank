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


        <table border="1">
            <tr>
                <td>Site Name</td>
                <td>Address</td>
                <td>Contact </td>
                <td>Hours</td>
                <td>Type </td>
                <td>Count</td>

            </tr>
            <c:forEach items="${allRoomBunk}" var="roomBunkCount">
                <tr>
                    <td>${roomBunkCount.siteName}</td>
                    <td>${roomBunkCount.address}</td>
                    <td>${roomBunkCount.contactNumber}</td>
                    <td>${roomBunkCount.hours}</td>
                    <td>${roomBunkCount.roomBunkTypeString}</td>
                    <td>${roomBunkCount.roomBunkCount}</td>

                </tr>
            </c:forEach>




        </table>

        <tr>
            <td colspan="2" align="center"><input type="submit" value="Return to Service"></td>
        </tr>

        <tr>
            <td colspan="2" align="center"><input type="submit" value="Logout"></td>
        </tr>




    </div>
</body>
</html>